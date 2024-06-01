package com.bkcoding.garagegurufyp_user.repository.chat

import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun createConversationIfNotExists(endUserId: String): Flow<Result<String>>
    fun sendMessage(message: ChatMessage)
}