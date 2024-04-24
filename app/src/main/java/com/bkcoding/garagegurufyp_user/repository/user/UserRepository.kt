package com.bkcoding.garagegurufyp_user.repository.user

import com.bkcoding.garagegurufyp_user.dto.User
import com.bkcoding.garagegurufyp_user.repository.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun storeUserToDatabase(user: User): Flow<Result<String>>
}