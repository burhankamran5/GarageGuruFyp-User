package com.bkcoding.garagegurufyp_user.network

import com.bkcoding.garagegurufyp_user.repository.fcm.NotificationReq
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("{projectId}/messages:send")
    suspend fun sendPushNotification(
        @Path("projectId") firebaseProjectId: String,
        @Header("Authorization") authHeader: String,
        @Body notificationReq: NotificationReq
    )
}