package com.bkcoding.garagegurufyp_user.dto

import com.google.firebase.database.Exclude

data class Customer(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val city: String = "",
    val phoneNumber: String = "",
    val token: String = "",
    @get:Exclude val password: String = "",
    @get:Exclude val confirmPassword: String = ""
)