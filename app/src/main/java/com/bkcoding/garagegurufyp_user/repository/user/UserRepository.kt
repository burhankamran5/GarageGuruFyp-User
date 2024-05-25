package com.bkcoding.garagegurufyp_user.repository.user

import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.repository.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun storeUserToDatabase(customer: Customer): Flow<Result<String>>
    fun storeGarageToDatabase(garage: Garage): Flow<Result<String>>
    fun uploadGarageImages(garage: Garage): Flow<Result<List<String>>>
    fun getCustomerFromDb(userId: String): Flow<Result<Customer>>
    fun getGarageFromDb(userId: String): Flow<Result<Garage>>

}