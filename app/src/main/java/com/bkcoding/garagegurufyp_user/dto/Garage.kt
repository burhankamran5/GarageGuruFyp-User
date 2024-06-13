package com.bkcoding.garagegurufyp_user.dto

import android.net.Uri
import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Garage(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    @get:Exclude val password: String = "",
    @get:Exclude val confirmPassword: String = "",
    @get:Exclude val imageUris: List<Uri> = emptyList(),
    val images: List<String> = emptyList(),
    val city: String = "",
    val description: String = "",
    val phoneNumber: String = "",
    val location: String = "",
    val rating: String = "",
    val employeeCount: String = "",
    val token: String = "",
    val approvalStatus: String = ApprovalStatus.PENDING.name
): Parcelable

enum class ApprovalStatus{
    PENDING,
    APPROVED,
    DECLINED
}
