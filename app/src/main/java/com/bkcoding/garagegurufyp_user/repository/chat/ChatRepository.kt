package com.bkcoding.garagegurufyp_user.repository.chat

import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import com.bkcoding.garagegurufyp_user.dto.Conversation
import kotlinx.coroutines.flow.Flow
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.repository.fcm.Message

interface ChatRepository {
    fun createConversationIfNotExists(conversation: Conversation): Flow<Result<String>>
    fun sendMessage(message: ChatMessage)
    fun fetchConversations(): Flow<Result<List<Conversation>>>
    fun fetchLastMessage(userId: String): Flow<Result<String>>
    fun loadMessages(endUserId: String): Flow<Result<ChatMessage>>
}