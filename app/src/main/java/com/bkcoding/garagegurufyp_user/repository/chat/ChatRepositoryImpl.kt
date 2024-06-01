package com.bkcoding.garagegurufyp_user.repository.chat

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
                        val conversation = Conversation(timeStamp = ServerValue.TIMESTAMP)
                        conversationsRef.child(endUserId).child(currentUserId).setValue(conversation)
                        conversationsRef.child(currentUserId).child(endUserId).setValue(conversation)
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
}