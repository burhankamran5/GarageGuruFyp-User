package com.bkcoding.garagegurufyp_user.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.extensions.readObject
import com.bkcoding.garagegurufyp_user.extensions.writeObject
import com.google.gson.Gson

class UserPreferences(context: Context) {
    companion object{
        const val USER = "user"
        const val GARAGE = "garage"
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

    fun updateUser(customer: Customer) {
        sharedPreferences.writeObject(gson, USER, customer)
    }

    fun getUser() = sharedPreferences.readObject<Customer>(gson, USER)

    fun updateGarage(garage: Garage) {
        sharedPreferences.writeObject(gson, GARAGE, garage)
    }

    fun getGarage() = sharedPreferences.readObject<Garage>(gson, GARAGE)

    fun signOut(){
        sharedPreferences.edit().remove(USER_TYPE).apply()
        sharedPreferences.edit().remove(USER).apply()
        sharedPreferences.edit().remove(GARAGE).apply()
    }
}