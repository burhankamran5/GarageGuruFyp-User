package com.bkcoding.garagegurufyp_user

import com.bkcoding.garagegurufyp_user.repository.fcm.FcmRepository
import com.google.firebase.messaging.FirebaseMessagingService
import javax.inject.Inject

class MyFirebaseMessagingService:  FirebaseMessagingService() {
    @Inject
    lateinit var fcmRepository: FcmRepository
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        fcmRepository.updateFcmToken(token)
    }
}