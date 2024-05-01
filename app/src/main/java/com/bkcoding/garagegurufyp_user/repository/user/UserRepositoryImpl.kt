package com.bkcoding.garagegurufyp_user.repository.user

import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.User
import com.bkcoding.garagegurufyp_user.repository.Result
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val databaseReference: DatabaseReference,
    private val storageReference: StorageReference
): UserRepository {
    override fun storeUserToDatabase(user: User): Flow<Result<String>> = callbackFlow {
        trySend(Result.Loading)
        databaseReference.child("Users").child(user.id).setValue(user)
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
        databaseReference.child("Garages").child(garage.id).setValue(garage)
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
        val uploadTasks = garage.imageUris.map { storageReference.child("GarageImages").child(garage.id).putFile(it) }
        Tasks.whenAllSuccess<UploadTask.TaskSnapshot>(uploadTasks).addOnSuccessListener{ imageTasks ->
            val downloadUrls = mutableListOf<String>()
            GlobalScope.launch {
                imageTasks.forEach {
                    downloadUrls.add(it.storage.downloadUrl.await().toString())
                }
                trySend(Result.Success(downloadUrls))
            }
        }.addOnFailureListener{
            Result.Failure(it)
        }
        awaitClose {
            close()
        }
    }
}