package com.bkcoding.garagegurufyp_user.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.bkcoding.garagegurufyp_user.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun createUserWithPhone(
        mobile: String,
        activity: Activity
    ) = authRepository.createUserWithPhone(mobile, activity)
}