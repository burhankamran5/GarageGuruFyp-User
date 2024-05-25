package com.bkcoding.garagegurufyp_user.ui.login

import androidx.lifecycle.ViewModel
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserStorageVM @Inject constructor(private val userPreferences: UserPreferences) :
    ViewModel() {

    var customer = Customer()
    var garage = Garage()
    fun isFirstLaunch() = userPreferences.isFirstLaunch

    fun setIsFirstLaunch() {
        userPreferences.isFirstLaunch = false
    }

    fun saveUserType(userType: String)  { userPreferences.userType = userType }

    fun getUserType() = userPreferences.userType
}