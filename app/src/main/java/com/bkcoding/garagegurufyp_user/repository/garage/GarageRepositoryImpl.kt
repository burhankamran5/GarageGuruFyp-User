package com.bkcoding.garagegurufyp_user.repository.garage

import android.content.Context
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Bid
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import com.bkcoding.garagegurufyp_user.utils.FirebaseRef
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GarageRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val databaseReference: DatabaseReference,
    private val userPreferences: UserPreferences,
) : GarageRepository {

    override fun getRequest() = callbackFlow {
        trySend(Result.Loading)
        val requestList = mutableListOf<Request>()
        databaseReference.child(FirebaseRef.REQUEST).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                for (ds in dataSnapshot.children) {
                    val request: Request? = ds.getValue(Request::class.java)
                    if (request != null) {
                        requestList.add(request)
                    }
                }
                trySend(Result.Success(requestList))
            } else {
                trySend(Result.Failure(Exception("No Request found with these details")))
            }
        }.addOnFailureListener {
            trySend(Result.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    override fun bidOnRequest(requestId: String, bid: Bid): Flow<Result<String>> = callbackFlow {
        val bidId = userPreferences.userId ?: return@callbackFlow
        trySend(Result.Loading)
        databaseReference.child(FirebaseRef.REQUEST).child(requestId).child("bids").child(bidId).setValue(bid.copy(id = bidId))
            .addOnSuccessListener {
                trySend(Result.Success("Bids Added"))
            }
            .addOnFailureListener {
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
                trySend(Result.Failure(java.lang.Exception(context.getString(R.string.no_customer_found_with_these_details))))
            }
        }.addOnFailureListener {
            Result.Failure(it)
        }
        awaitClose {
            close()
        }
    }

}