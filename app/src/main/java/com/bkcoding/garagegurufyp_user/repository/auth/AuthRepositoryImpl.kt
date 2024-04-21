package com.bkcoding.garagegurufyp_user.repository.auth

import android.app.Activity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

): AuthRepository {
    override fun createUserWithPhone(phone: String, activity: Activity): Flow<Result<String>> {
        TODO("Not yet implemented")
    }
}