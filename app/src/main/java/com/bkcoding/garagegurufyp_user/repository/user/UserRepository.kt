package com.bkcoding.garagegurufyp_user.repository.user

import android.net.Uri
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.User
import com.bkcoding.garagegurufyp_user.repository.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun storeUserToDatabase(user: User): Flow<Result<String>>
    fun storeGarageToDatabase(garage: Garage): Flow<Result<String>>
    fun uploadGarageImages(garage: Garage): Flow<Result<List<String>>>
}