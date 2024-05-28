package com.bkcoding.garagegurufyp_user.repository.auth

import android.app.Activity
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.repository.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun sendOtp(
        phone:String,
        activity: Activity?
    ) : Flow<Result<String>>

    fun createFirebaseUser(otp: String, customer: Customer?, garage: Garage?): Flow<Result<String>>
    fun login(email: String, password: String): Flow<Result<String>>

    fun signOutFirebaseUser()
}