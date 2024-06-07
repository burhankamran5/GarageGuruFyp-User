package com.bkcoding.garagegurufyp_user.repository.garage

import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.repository.Result
import kotlinx.coroutines.flow.Flow

interface GarageRepository {
    fun getRequest(): Flow<Result<List<Request>>>
}