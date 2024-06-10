package com.bkcoding.garagegurufyp_user.ui.login

import androidx.lifecycle.ViewModel
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserStorageVM @Inject constructor(val userPreferences: UserPreferences) :
    ViewModel() {
    // Both are used for signup process. Should not be used to display user data
    var customer = Customer()
    var garage = Garage()

    val userType get() = userPreferences.userType
    val userId get() = userPreferences.userId
    fun isFirstLaunch() = userPreferences.isFirstLaunch

    fun setIsFirstLaunch() {
        userPreferences.isFirstLaunch = false
    }

    fun getSavedCustomer() = userPreferences.getCustomer()

    fun getSavedGarage() = userPreferences.getGarage()

    fun updateCustomerPref(customer: Customer) = userPreferences.updateCustomer(customer)

    fun updateGaragePref(garage: Garage) = userPreferences.updateGarage(garage)

    fun updateUserData(userType: String, userId: String)  {
        userPreferences.userType = userType
        userPreferences.userId = userId
    }

}