package com.bkcoding.garagegurufyp_user.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson

class UserPreferences(context: Context) {
    companion object{
        const val IS_FIRST_LAUNCH = "is_first_launch"
        const val USER_TYPE = "user_type"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private val gson by lazy {  Gson() }

    var isFirstLaunch: Boolean
        get() = sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true)
        set(value) = sharedPreferences.edit { putBoolean(IS_FIRST_LAUNCH, value) }

    var userType: String?
        get() = sharedPreferences.getString(USER_TYPE, null)
        set(value) = sharedPreferences.edit { putString(USER_TYPE, value) }


    fun signOut(){
        sharedPreferences.edit().remove(USER_TYPE).apply()
    }
}