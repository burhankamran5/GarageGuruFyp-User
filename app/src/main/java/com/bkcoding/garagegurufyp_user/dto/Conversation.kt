package com.bkcoding.garagegurufyp_user.dto

data class Conversation(
    val seen: Boolean = false,
    val createdAt: Any = 0,
    val userId: String = "",
    val userName: String = "",
    val profileImage: String = ""
)