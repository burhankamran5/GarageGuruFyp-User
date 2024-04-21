package com.bkcoding.garagegurufyp_user.repository.auth

import android.app.Activity
import com.bkcoding.garagegurufyp_user.repository.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun sendOtp(
        phone:String,
        activity: Activity?
    ) : Flow<Result<String>>
}