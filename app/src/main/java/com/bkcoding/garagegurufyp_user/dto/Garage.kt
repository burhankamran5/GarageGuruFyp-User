package com.bkcoding.garagegurufyp_user.dto

import android.net.Uri
import com.google.firebase.database.Exclude

data class Garage(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    @get:Exclude val password: String = "",
    @get:Exclude val confirmPassword: String = "",
    @get:Exclude val imageUris: List<Uri> = emptyList(),
    val images: List<String> = emptyList(),
    val city: String = "",
    val phoneNumber: String = "",
    val location: String = "",
    val rating: String = "",
    val employeeCount: String = "",
    val token: String = "",
    val approvalStatus: String = ApprovalStatus.PENDING.name
)

enum class ApprovalStatus{
    PENDING,
    APPROVED,
    DECLINED
}
