package com.bkcoding.garagegurufyp_user.ui.chat

import androidx.lifecycle.ViewModel
import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import com.bkcoding.garagegurufyp_user.repository.chat.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
): ViewModel() {
    fun createConversationIfNotExists(endUserId: String) = chatRepository.createConversationIfNotExists(endUserId)
    fun sendMessage(message: ChatMessage) = chatRepository.sendMessage(message)
}