package com.bkcoding.garagegurufyp_user.dto

import com.google.firebase.database.Exclude

data class User(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val city: String = "",
    @Exclude val password: String = "",
)
