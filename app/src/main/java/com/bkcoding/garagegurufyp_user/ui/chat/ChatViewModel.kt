package com.bkcoding.garagegurufyp_user.ui.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.repository.chat.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
): ViewModel() {

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")
    var conversationListResponse by mutableStateOf<List<Conversation>?>(null)

    fun createConversationIfNotExists(conversation: Conversation) = chatRepository.createConversationIfNotExists(conversation)
    fun sendMessage(message: ChatMessage) = chatRepository.sendMessage(message)
    fun fetchConversations() = viewModelScope.launch {
        chatRepository.fetchConversations().collect{result->
            when (result) {
                Result.Loading -> {
                    isLoading = true
                }

                is Result.Success -> {
                    isLoading = false
                    conversationListResponse = result.data
                }

                is Result.Failure -> {
                    isLoading = false
                    error = result.exception.message ?: "Error"
                }
            }
        }
    }
}