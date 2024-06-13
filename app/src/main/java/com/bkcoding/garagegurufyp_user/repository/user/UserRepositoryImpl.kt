package com.bkcoding.garagegurufyp_user.repository.user

import android.content.Context
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.ApprovalStatus
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.utils.FirebaseRef
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val databaseReference: DatabaseReference,
    private val storageReference: StorageReference
): UserRepository {
    private val requestsRef =  databaseReference.child(FirebaseRef.REQUEST)
    override fun storeUserToDatabase(customer: Customer): Flow<Result<String>> = callbackFlow {
        trySend(Result.Loading)
        databaseReference.child(FirebaseRef.CUSTOMERS).child(customer.id).setValue(customer)
            .addOnSuccessListener {
                trySend(Result.Success("Data inserted Successfully.."))
            }.addOnFailureListener{
                trySend(Result.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun storeGarageToDatabase(garage: Garage): Flow<Result<String>> = callbackFlow{
        trySend(Result.Loading)
        databaseReference.child(FirebaseRef.GARAGES).child(garage.id).setValue(garage)
            .addOnSuccessListener {
                trySend(Result.Success("Data inserted Successfully.."))
            }.addOnFailureListener{
                trySend(Result.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun uploadGarageImages(garage: Garage): Flow<Result<List<String>>> = callbackFlow{
        trySend(Result.Loading)
        val uploadTasks = garage.imageUris.map { storageReference.child(FirebaseRef.GARAGE_IMAGES).child(garage.id).putFile(it) }
        Tasks.whenAllSuccess<UploadTask.TaskSnapshot>(uploadTasks).addOnSuccessListener{ imageTasks ->
            val downloadUrls = mutableListOf<String>()
            GlobalScope.launch {
                imageTasks.forEach {
                    downloadUrls.add(it.storage.downloadUrl.await().toString())
                }
                trySend(Result.Success(downloadUrls))
            }
        }.addOnFailureListener{
            trySend(Result.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    override fun getCustomerFromDb(userId: String): Flow<Result<Customer>> = callbackFlow{
        databaseReference.child(FirebaseRef.CUSTOMERS).child(userId).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()){
                val customer = dataSnapshot.getValue(Customer::class.java)
                customer?.let { trySend(Result.Success(customer)) }
            } else{
                trySend(Result.Failure(Exception(context.getString(R.string.no_customer_found_with_these_details))))
            }
        }.addOnFailureListener {
            Result.Failure(it)
        }
        awaitClose {
            close()
        }
    }

    override fun getGarageFromDb(userId: String): Flow<Result<Garage>> = callbackFlow{
        databaseReference.child(FirebaseRef.GARAGES).child(userId).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()){
                val garage = dataSnapshot.getValue(Garage::class.java)
                val errorMessage = when{
                    garage == null -> context.getString(R.string.no_garage_found_with_these_details)
                    garage.approvalStatus == ApprovalStatus.PENDING.name -> "Your approval is pending from Admin"
                    garage.approvalStatus == ApprovalStatus.DECLINED.name -> "Your garage creation request was declined"
                    else -> ""
                }
                if (errorMessage.isEmpty() && garage != null){
                    trySend(Result.Success(garage))
                } else{
                    trySend(Result.Failure(Exception(errorMessage)))
                }
            } else{
                trySend(Result.Failure(Exception("No Garage found with these details")))
            }
        }.addOnFailureListener {
            Result.Failure(it)
        }
        awaitClose {
            close()
        }
    }

    override fun getGarages(): Flow<Result<List<Garage>>> = callbackFlow {
        val garageList = mutableListOf<Garage>()
        databaseReference.child(FirebaseRef.GARAGES).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()){
                for (ds in dataSnapshot.children) {
                    val garage: Garage? = ds.getValue(Garage::class.java)
                    if (garage != null) {
                        garageList.add(garage)
                    }
                }
                trySend(Result.Success(garageList))
            } else{
                trySend(Result.Failure(Exception("No Customer found with these details")))
            }
        }.addOnFailureListener {
            trySend(Result.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun postRequest(request: Request): Flow<Result<String>> = callbackFlow {
        trySend(Result.Loading)
        val key = databaseReference.push().key
        val uploadTasks = request.imageUris.map { storageReference.child(FirebaseRef.REQUEST_IMAGES).child(key.orEmpty()).putFile(it) }
        Tasks.whenAllSuccess<UploadTask.TaskSnapshot>(uploadTasks).addOnSuccessListener{ imageTasks ->
            val downloadUrls = mutableListOf<String>()
            GlobalScope.launch {
                imageTasks.forEach {
                    downloadUrls.add(it.storage.downloadUrl.await().toString())
                }
                val newRequest = Request(
                    id = key ?: "",
                    images = downloadUrls,
                    imageUris = request.imageUris,
                    description = request.description,
                    carModel = request.carModel,
                    assignedGarage = request.assignedGarage,
                    bids = request.bids,
                    status = request.status,
                    city = request.city,
                    customer = request.customer
                )
                requestsRef.child(key ?: return@launch).setValue(newRequest)
                    .addOnSuccessListener {
                        trySend(Result.Success("Data inserted Successfully.."))

                    }.addOnFailureListener{
                        trySend(Result.Failure(it))
                    }
            }
        }.addOnFailureListener{
            trySend(Result.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    override fun getRequests() = callbackFlow {
        trySend(Result.Loading)
        val requestList = mutableListOf<Request>()
        requestsRef.get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()){
                for (ds in dataSnapshot.children) {
                    val request: Request? = ds.getValue(Request::class.java)
                    if (request != null) {
                        requestList.add(request)
                    }
                }
                trySend(Result.Success(requestList))
            } else{
                trySend(Result.Failure(Exception("No Request found with these details")))
            }
        }.addOnFailureListener {
            trySend(Result.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    override fun updateRequest(request: Request): Flow<Result<String>> = callbackFlow {
        trySend(Result.Loading)
        requestsRef.child(request.id).setValue(request).addOnSuccessListener {
            trySend(Result.Success("Request Updated"))
        }.addOnFailureListener {
            trySend(Result.Failure(it))
        }
        awaitClose {
            close()
        }
    }
}