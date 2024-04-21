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
import kotlinx.coroutines.channels.awaitClose
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    private val testPhoneNumber = "3001234567"
    private lateinit var verificationCode: String
    private var forceResendToken: ForceResendingToken? = null

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
                trySend(Result.Success("A verification code has been sent to your phone"))
                this@AuthRepositoryImpl.verificationCode = verificationCode
                this@AuthRepositoryImpl.forceResendToken = forceResendToken
            }
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+92$testPhoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .apply { activity?.let { setActivity(it) } }
            .apply { forceResendToken?.let { setForceResendingToken(it) } }
            .setCallbacks(verificationCallbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        awaitClose {
            close()
        }
    }
}