package com.bkcoding.garagegurufyp_user.repository.garage

import com.bkcoding.garagegurufyp_user.dto.Bid
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.repository.Result
import kotlinx.coroutines.flow.Flow

interface GarageRepository {
    fun getRequest(): Flow<Result<List<Request>>>

    fun bidOnRequest(requestId: String, bid: Bid): Flow<Result<String>>

    fun getCustomerFromDb(userId: String): Flow<Result<Customer>>
}