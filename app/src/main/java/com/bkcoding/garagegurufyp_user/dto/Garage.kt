package com.bkcoding.garagegurufyp_user.dto

import com.google.firebase.database.Exclude

data class Garage(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    @Exclude val password: String = "",
    val images: List<String> = emptyList(),
    val city: String = "",
    val phoneNumber: Int = 0,
    val location: String = "",
    val rating: String = "",
    val employeeCount: Int = 0,
    val isApproved: Boolean = false,
)
