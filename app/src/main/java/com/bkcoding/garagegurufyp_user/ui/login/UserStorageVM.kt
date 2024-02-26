package com.bkcoding.garagegurufyp_user.ui.login

import androidx.lifecycle.ViewModel
import com.bkcoding.garagegurufyp_user.sharedpref.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserStorageVM @Inject constructor(private val preferencesManager: PreferencesManager) :
    ViewModel() {


    fun isFirstLaunch() = preferencesManager.isFirstLaunch

    fun setIsFirstLaunch() {
        preferencesManager.isFirstLaunch = false
    }

}