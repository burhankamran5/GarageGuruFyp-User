package com.bkcoding.garagegurufyp_user.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Conversation(
    val seen: Boolean = false,
    val createdAt: @RawValue Any? = null,
    val userId: String = "",
    val userName: String = "",
    val profileImage: String = ""
): Parcelable