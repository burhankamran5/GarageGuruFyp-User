package com.bkcoding.garagegurufyp_user.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson

class UserPreferences(context: Context) {
    companion object{
        const val IS_FIRST_LAUNCH = "is_first_launch"
        const val USER_TYPE = "user_type"
        const val USER_ID = "user_id"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var isFirstLaunch: Boolean
        get() = sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true)
        set(value) = sharedPreferences.edit { putBoolean(IS_FIRST_LAUNCH, value) }

    var userType: String?
        get() = sharedPreferences.getString(USER_TYPE, null)
        set(value) = sharedPreferences.edit { putString(USER_TYPE, value) }

    var userId: String?
        get() = sharedPreferences.getString(USER_ID, null)
        set(value) = sharedPreferences.edit { putString(USER_ID, value) }


    fun signOut() {
        userType = null
        userId = null
    }
}