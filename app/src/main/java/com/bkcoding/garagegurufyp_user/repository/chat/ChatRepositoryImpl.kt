package com.bkcoding.garagegurufyp_user.repository.chat

import android.util.Log
import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import com.bkcoding.garagegurufyp_user.ui.login.UserType
import com.bkcoding.garagegurufyp_user.utils.FirebaseRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
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
    private val currentUserId = userPreferences.userId

    override fun createConversationIfNotExists(conversation: Conversation): Flow<Result<String>> = callbackFlow {
        val userType = userPreferences.userType ?: return@callbackFlow
        currentUserId ?: return@callbackFlow
        val endUserId = conversation.userId
        val (currentUserName, currentUserProfile) = getUserNameAndProfile(userType)
        conversationsRef.child(currentUserId).addListenerForSingleValueEvent(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.hasChild(endUserId)){
                        // Create 2 conversations 1. From currentUser to endUser 2. From endUser to CurrentUser
                        val ownConversation = conversation.copy(userId = currentUserId, userName = currentUserName, profileImage = currentUserProfile)
                        conversationsRef.child(endUserId).child(currentUserId).setValue(ownConversation)
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
        messagesRef.child(senderId).child(receiverId).child(messageKey).setValue(message)
        messagesRef.child(receiverId).child(senderId).child(messageKey).setValue(message)
    }

    override fun fetchConversations(): Flow<Result<List<Conversation>>> = callbackFlow{
        currentUserId ?: return@callbackFlow
        val conversationList = mutableListOf<Conversation>()
        trySend(Result.Loading)
        conversationsRef.child(currentUserId).addListenerForSingleValueEvent(object : ValueEventListener{
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
                    trySend(Result.Success(emptyList()))
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

    override fun fetchLastMessage(userId: String): Flow<Result<String>> = callbackFlow{
        currentUserId ?: return@callbackFlow
        val lastMessageRef = messagesRef.child(currentUserId).child(userId).limitToLast(1)
        lastMessageRef.addChildEventListener(object : SimpleChildEventListener{
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val lastMessage = dataSnapshot.child("text").value.toString()
                trySend(Result.Success(lastMessage))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Result.Failure(error.toException()))
            }

        })
        awaitClose {
            close()
        }
    }

    override fun loadMessages(endUserId: String): Flow<Result<ChatMessage>> = callbackFlow{
        currentUserId ?: return@callbackFlow
        messagesRef.child(currentUserId).child(endUserId).addChildEventListener(object : SimpleChildEventListener{
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val message = dataSnapshot.getValue(ChatMessage::class.java)
                message?.let { trySend(Result.Success(message)) }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Result.Failure(error.toException()))
            }
        })
        awaitClose {
            close()
        }
    }

    private fun getUserNameAndProfile(userType: String) = if (userType == UserType.Customer.name) {
        Pair(userPreferences.getCustomer()?.name.orEmpty(), "")
    } else {
        Pair(userPreferences.getGarage()?.name.orEmpty(), userPreferences.getGarage()?.images?.firstOrNull().orEmpty())
    }
}