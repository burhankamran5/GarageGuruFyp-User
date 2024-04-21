package com.bkcoding.garagegurufyp_user.repository.auth

import android.app.Activity
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun createUserWithPhone(
        phone:String,
        activity: Activity
    ) : Flow<Result<String>>
}