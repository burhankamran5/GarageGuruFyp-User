package com.bkcoding.garagegurufyp_user.repository.auth

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.bkcoding.garagegurufyp_user.repository.Result
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.channels.awaitClose
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    private val testPhoneNumbers = mapOf(
        "burhan" to "+923334825710",
        "hanzla" to "+923427991399",
        "waqas" to "+923045593294",
        "naveed" to "+923337291332",
        "mazhar" to "+923041537702"
    )
    private lateinit var verificationCode: String

    override fun sendOtp(phone: String, activity: Activity?): Flow<Result<String>> = callbackFlow {
        trySend(Result.Loading)
        val verificationCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(excpetion: FirebaseException) {
                trySend(Result.Failure(excpetion))
            }

            override fun onCodeSent(verificationCode: String, forceResendToken: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationCode, forceResendToken)
                trySend(Result.Success("A verification code has been sent to your phone"))
                this@AuthRepositoryImpl.verificationCode = verificationCode
            }
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+92$phone")
            .setTimeout(60L, TimeUnit.SECONDS)
            .apply { activity?.let { setActivity(it) } }
            .setCallbacks(verificationCallbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        awaitClose {
            close()
        }
    }
}