package com.bkcoding.garagegurufyp_user.ui.garage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bkcoding.garagegurufyp_user.dto.Bid
import com.bkcoding.garagegurufyp_user.dto.NotificationAction
import com.bkcoding.garagegurufyp_user.dto.NotificationData
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.repository.fcm.FcmRepository
import com.bkcoding.garagegurufyp_user.repository.fcm.Message
import com.bkcoding.garagegurufyp_user.repository.fcm.Notification
import com.bkcoding.garagegurufyp_user.repository.fcm.NotificationReq
import com.bkcoding.garagegurufyp_user.repository.garage.GarageRepository
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import com.google.firebase.database.ServerValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarageViewModel @Inject constructor(
    private val garageRepository: GarageRepository,
    private val fcmRepository: FcmRepository,
    val userPreferences: UserPreferences
): ViewModel() {

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")
    var getRequestResponse by mutableStateOf<List<Request>?>(null)
    var getNotificationListResponse by mutableStateOf<List<NotificationData>?>(null)

    fun getRequest() = viewModelScope.launch {
        isLoading = true
        garageRepository.getRequest().collect{
            if(it is Result.Success){
                isLoading = false
                getRequestResponse = it.data
            }else if(it is Result.Failure){
                isLoading = false
                error = it.exception.message ?: "Error"
            }
        }
    }

    fun bidOnRequest(requestId: String, bid: Bid) = viewModelScope.launch {
        isLoading = true
        garageRepository.bidOnRequest(requestId, bid).collect{
            if(it is Result.Success){
                isLoading = false
                getCustomerFromDb(bid.customer?.id.orEmpty()).collect{customerResult->
                    if(customerResult is Result.Success){
                        sendPushNotification(
                            NotificationReq(
                                Message(
                                    token = customerResult.data.token,
                                    notification = Notification(title = "Bid", body = "${bid.garage?.name} bid on your Request!")
                                )
                            )
                        )
                        addPushNotificationOnDB(
                            NotificationData(
                                from = bid.garage?.id.orEmpty(),
                                to = bid.customer?.id.orEmpty(),
                                thumbnail = bid.garage?.images?.getOrNull(0).orEmpty(),
                                title = NotificationAction.BID.label,
                                description = "${bid.garage?.name} bid on your Request!",
                                sentAt = ServerValue.TIMESTAMP
                            )
                        )
                    }
                }
            }else if(it is Result.Failure){
                isLoading = false
                error = it.exception.message ?: "Error"
            }
        }
    }

    fun sendPushNotification(notificationReq: NotificationReq) = viewModelScope.launch {
        fcmRepository.sendPushNotification(notificationReq)
    }

    fun addPushNotificationOnDB(notificationData: NotificationData) = viewModelScope.launch {
        garageRepository.addPushNotificationOnDB(notificationData).collect {
            if (it is Result.Success) {
                isLoading = false
            } else if (it is Result.Failure) {
                error = it.exception.message ?: "Error"
            }
        }
    }

    fun getPushNotificationFromDB() = viewModelScope.launch {
        isLoading = true
        garageRepository.getPushNotificationFromDB(id = userPreferences.userId.orEmpty()).collect {
            if (it is Result.Success) {
                isLoading = false
                getNotificationListResponse = it.data
            } else if (it is Result.Failure) {
                isLoading = false
                error = it.exception.message ?: "Error"
            }
        }
    }

    fun getCustomerFromDb(userId: String) = garageRepository.getCustomerFromDb(userId)

}