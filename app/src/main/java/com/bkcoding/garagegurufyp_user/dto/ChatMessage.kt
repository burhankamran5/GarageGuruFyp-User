package com.bkcoding.garagegurufyp_user.dto

data class ChatMessage(
    val text: String = "",
    val isSeen: Boolean = false,
    val type: String =  MessageType.TEXT.name,
    val senderId: String = "",
    val receiverId: String = "",
    val sentAt: Any = 0
)

enum class MessageType{
    TEXT, IMAGE
}