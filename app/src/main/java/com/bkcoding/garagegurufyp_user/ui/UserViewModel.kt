package com.bkcoding.garagegurufyp_user.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.repository.fcm.FcmRepository
import com.bkcoding.garagegurufyp_user.repository.fcm.NotificationReq
import com.bkcoding.garagegurufyp_user.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val fcmRepository: FcmRepository
): ViewModel() {
    fun storeUserToDb(customer: Customer) = userRepository.storeUserToDatabase(customer)
    fun uploadGarageImages(garage: Garage) = userRepository.uploadGarageImages(garage)
    fun storeGarageToDb(garage: Garage) = userRepository.storeGarageToDatabase(garage)
    fun getCustomerFromDb(userId: String) = userRepository.getCustomerFromDb(userId)
    fun getGarageFromDb(userId: String) = userRepository.getGarageFromDb(userId)
    fun refreshFcmToken() = fcmRepository.refreshFcmToken()
    fun sendPushNotification(notificationReq: NotificationReq) = viewModelScope.launch {
        fcmRepository.sendPushNotification(notificationReq)
    }
}