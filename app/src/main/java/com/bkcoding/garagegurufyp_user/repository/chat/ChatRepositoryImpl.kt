package com.bkcoding.garagegurufyp_user.repository.chat

import android.util.Log
import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import com.bkcoding.garagegurufyp_user.utils.FirebaseRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.bkcoding.garagegurufyp_user.repository.Result
import java.lang.Exception
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val databaseReference: DatabaseReference,
    private val userPreferences: UserPreferences
): ChatRepository {
    private val conversationsRef = databaseReference.child(FirebaseRef.CONVERSATIONS)
    private val messagesRef = databaseReference.child(FirebaseRef.MESSAGES)
    override fun createConversationIfNotExists(endUserId: String): Flow<Result<String>> = callbackFlow {
        val currentUserId = userPreferences.userId ?: return@callbackFlow
        conversationsRef.child(currentUserId).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.hasChild(endUserId)){
                        // Create 2 conversations 1. From currentUser to endUser 2. From endUser to CurrentUser
                        val ownConversation = Conversation(createdAt = ServerValue.TIMESTAMP, userId = currentUserId)
                        val endUserConversation = Conversation(createdAt = ServerValue.TIMESTAMP, userId = endUserId)
                        conversationsRef.child(endUserId).child(currentUserId).setValue(ownConversation)
                        conversationsRef.child(currentUserId).child(endUserId).setValue(endUserConversation)
                    }
                }

                override fun onCancelled(error: DatabaseError) {}

            }
        )

        awaitClose {
            close()
        }
    }

    override fun sendMessage(message: ChatMessage) {
        val senderId = message.senderId
        val receiverId = message.receiverId
        val messageKey = messagesRef.child(senderId).child(receiverId).push().key ?: return
        messagesRef.child(senderId).child(messageKey).setValue(message)
        messagesRef.child(receiverId).child(messageKey).setValue(message)
    }

    override fun fetchConversations(): Flow<Result<List<Conversation>>> = callbackFlow{
        val userId = userPreferences.userId ?: return@callbackFlow
        val conversationList = mutableListOf<Conversation>()
        trySend(Result.Loading)
        conversationsRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("TAG", "onDataChangeInfo: $dataSnapshot")
                if (dataSnapshot.exists()){
                    for (ds in dataSnapshot.children) {
                        val conversation: Conversation? = ds.getValue(Conversation::class.java)
                        if (conversation != null) {
                            conversationList.add(conversation)
                        }
                    }
                    trySend(Result.Success(conversationList))
                } else{
                    trySend(Result.Failure(Exception("No Customer found with these details")))
                }
            }

            override fun onCancelled(error: DatabaseError) {
               trySend(Result.Failure(error.toException()))
            }

        })
        awaitClose {
            close()
        }
    }
}