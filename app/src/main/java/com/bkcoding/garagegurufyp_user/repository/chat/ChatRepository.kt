package com.bkcoding.garagegurufyp_user.repository.chat

import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import com.bkcoding.garagegurufyp_user.dto.Conversation
import kotlinx.coroutines.flow.Flow
import com.bkcoding.garagegurufyp_user.repository.Result

interface ChatRepository {
    fun createConversationIfNotExists(endUserId: String): Flow<Result<String>>
    fun sendMessage(message: ChatMessage)
    fun fetchConversations(): Flow<Result<List<Conversation>>>
}