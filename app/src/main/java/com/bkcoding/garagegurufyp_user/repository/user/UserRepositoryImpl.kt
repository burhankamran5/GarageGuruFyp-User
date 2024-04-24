package com.bkcoding.garagegurufyp_user.repository.user

import android.os.ResultReceiver
import com.bkcoding.garagegurufyp_user.dto.User
import com.bkcoding.garagegurufyp_user.repository.Result
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val databaseReference: DatabaseReference
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

}