package com.bkcoding.garagegurufyp_user.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.User
import com.bkcoding.garagegurufyp_user.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun sendOtp(
        phoneNo: String,
        activity: Activity?
    ) = authRepository.sendOtp(phoneNo, activity)

    fun createUser(otp: String,  user: User?, garage: Garage?) = authRepository.createFirebaseUser(otp, user, garage)
}