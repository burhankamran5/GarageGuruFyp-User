package com.bkcoding.garagegurufyp_user.repository.fcm

import android.content.Context
import android.util.Log
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.network.ApiService
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import com.bkcoding.garagegurufyp_user.ui.login.UserType
import com.bkcoding.garagegurufyp_user.utils.FirebaseRef
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.database.DatabaseReference
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.StandardCharsets
import javax.inject.Inject

private const val TAG = "FcmRepositoryImpl"
class FcmRepositoryImpl @Inject constructor(
    private val userPreferences: UserPreferences,
    private val firebaseMessaging: FirebaseMessaging,
    private val databaseReference: DatabaseReference,
    private val apiService: ApiService,
    @ApplicationContext private val context: Context
): FcmRepository {
    override suspend fun sendPushNotification(notificationReq: NotificationReq) = withContext(Dispatchers.IO) {
        val oAuth2Token = getAccessToken() ?: return@withContext
        try {
            apiService.sendPushNotification(
                firebaseProjectId = FirebaseRef.PROJECT_ID,
                authHeader = "Bearer $oAuth2Token",
                notificationReq = notificationReq
            )
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun refreshFcmToken() {
        userPreferences.userType ?: return
        userPreferences.userId ?: return
        firebaseMessaging.token.addOnCompleteListener {
            if (it.isComplete) {
                val token = it.result.toString()
                updateFcmToken(token)
                Log.d(TAG, "refreshFcmToken: $token")
            }
        }
    }

    override fun updateFcmToken(token: String) {
        val userType = userPreferences.userType ?: return
        val userId = userPreferences.userId ?: return
        val userDbRef = if (userType == UserType.Customer.name) FirebaseRef.CUSTOMERS else FirebaseRef.GARAGES
        databaseReference.child(userDbRef).child(userId).child(FirebaseRef.TOKEN).setValue(token)
    }

    private fun getAccessToken() = try {
            val jsonString = context.applicationContext.resources.openRawResource(
                R.raw.service_account
            ).bufferedReader().use { it.readText() }

            val firebaseMessagingScope = "https://www.googleapis.com/auth/firebase.messaging"
            val googleCredentials = GoogleCredentials
                .fromStream(jsonString.byteInputStream(StandardCharsets.UTF_8))
                .createScoped(listOf(firebaseMessagingScope))
            googleCredentials.refresh()
            googleCredentials.accessToken.tokenValue
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            null
        }
}