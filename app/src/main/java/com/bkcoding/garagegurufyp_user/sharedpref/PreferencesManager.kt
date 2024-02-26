package com.bkcoding.garagegurufyp_user.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var isFirstLaunch: Boolean
        get() = sharedPreferences.getBoolean("isFirstLaunch", true)
        set(value) = sharedPreferences.edit { putBoolean("isFirstLaunch", value) }

}