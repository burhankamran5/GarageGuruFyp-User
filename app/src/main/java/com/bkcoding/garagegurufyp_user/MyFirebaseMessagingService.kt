package com.bkcoding.garagegurufyp_user

import com.bkcoding.garagegurufyp_user.repository.fcm.FcmRepository
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService:  FirebaseMessagingService() {
    @Inject
    lateinit var fcmRepository: FcmRepository
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        fcmRepository.updateFcmToken(token)
    }
}