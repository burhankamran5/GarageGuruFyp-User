package com.bkcoding.garagegurufyp_user.repository.garage

import com.bkcoding.garagegurufyp_user.dto.Bid
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.utils.FirebaseRef
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GarageRepositoryImpl @Inject constructor(
    private val databaseReference: DatabaseReference,
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
        trySend(Result.Loading)
        databaseReference.child(FirebaseRef.REQUEST).child(requestId).child("bids").child(databaseReference.push().key ?: return@callbackFlow).setValue(bid)
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
}