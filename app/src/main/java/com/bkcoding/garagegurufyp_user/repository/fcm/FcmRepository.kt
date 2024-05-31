package com.bkcoding.garagegurufyp_user.repository.fcm

interface FcmRepository {
    suspend fun sendPushNotification(notificationReq: NotificationReq)
    fun refreshFcmToken()
    fun updateFcmToken(token: String)
}