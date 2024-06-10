package com.bkcoding.garagegurufyp_user.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bkcoding.garagegurufyp_user.repository.Result
import androidx.lifecycle.viewModelScope
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.repository.fcm.FcmRepository
import com.bkcoding.garagegurufyp_user.repository.fcm.NotificationReq
import com.bkcoding.garagegurufyp_user.repository.user.UserRepository
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val fcmRepository: FcmRepository,
    val userPreferences: UserPreferences
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

    var homeScreenUIState by mutableStateOf<Result<List<Garage>>?>(null)
    var postRequestResponse by mutableStateOf<Result<String>?>(null)
    var getRequestResponse by mutableStateOf<Result<List<Request>>?>(null)

    fun getGarages() = viewModelScope.launch {
        userRepository.getGarages().collect{
            homeScreenUIState = it
        }
    }

    fun postRequest(request: Request) = viewModelScope.launch {
        userRepository.postRequest(request).collect{
            postRequestResponse = it
        }
    }

    fun getRequest() = viewModelScope.launch {
        userRepository.getRequest().collect{
            getRequestResponse = it
        }
    }
}