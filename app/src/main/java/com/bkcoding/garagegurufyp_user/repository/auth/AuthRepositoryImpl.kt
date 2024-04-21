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
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    private val testPhoneNumber = "3001234567"
    private lateinit var verificationCode: String
    private var forceResendToken: ForceResendingToken? = null
    private var lastOtpSentTime = 0L
    override fun sendOtp(phone: String, activity: Activity?): Flow<Result<String>> = callbackFlow {
        trySend(Result.Loading)
        val verificationCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(excpetion: FirebaseException) {
                trySend(Result.Failure(excpetion))
            }

            override fun onCodeSent(verificationCode: String, forceResendToken: ForceResendingToken) {
                super.onCodeSent(verificationCode, forceResendToken)
                this@AuthRepositoryImpl.verificationCode = verificationCode
                this@AuthRepositoryImpl.forceResendToken = forceResendToken
                lastOtpSentTime = System.currentTimeMillis()
                trySend(Result.Success("A verification code has been sent to your phone"))
            }
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+92$testPhoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .apply { activity?.let { setActivity(it) } }
            .apply { forceResendToken?.let { setForceResendingToken(it) } }
            .setCallbacks(verificationCallbacks)
            .build()

        if (System.currentTimeMillis() - lastOtpSentTime < 60_000L){
            trySend(Result.Failure(Exception("Please wait 60 secs before requesting an OTP again")))
        } else {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        awaitClose {
            close()
        }
    }
}