package com.bkcoding.garagegurufyp_user.dto

import com.google.firebase.database.Exclude

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val city: String = "",
    val phoneNumber: String = "",
    @Exclude val password: String = "",
    @Exclude val confirmPassword: String = "",
)
