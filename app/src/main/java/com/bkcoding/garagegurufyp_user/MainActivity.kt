package com.bkcoding.garagegurufyp_user

import UserHomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bkcoding.garagegurufyp_user.navigation.Navigation
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.sharedpref.UserPreferences
import com.bkcoding.garagegurufyp_user.ui.AuthViewModel
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.login.UserType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.bkcoding.garagegurufyp_user.navigation.GarageApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userStorageVM: UserStorageVM by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private var navController : NavHostController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        validateUser()
        setContent {
            navController = rememberNavController()
            Navigation(
                userStorageVM = userStorageVM,
                navController =  navController ?: return@setContent,
                startDestination = getStartDestination()
            )
            val navController = rememberNavController()
            var isGoToHomeScreen by rememberSaveable { mutableStateOf(false) }
            GarageGuruFypUserTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GarageApp(
                        navController = navController,
                        isGoToHomeScreen = isGoToHomeScreen,
                    ) {
                        isGoToHomeScreen = true
                    }
                }
            }
        }
    }
}



    private fun getStartDestination() = when {
        userStorageVM.isFirstLaunch() -> Screen.OnBoarding.route
        userStorageVM.userType == UserType.Customer.name -> Screen.CustomerHomeScreen.route
        userStorageVM.userType == UserType.Garage.name -> Screen.GarageHomeScreen.route
        else -> Screen.LoginScreen.route
    }

    private fun validateUser() {
        lifecycleScope.launch {
            if (userStorageVM.userType == UserType.Customer.name) {
                getCustomerData()
            } else {
                getGarageData()
            }
        }
    }

    private suspend fun getCustomerData(){
        userViewModel.getCustomerFromDb(userStorageVM.userId.orEmpty()).collect { result ->
            if (result is Result.Success) {
                userStorageVM.updateCustomerPref(result.data)
            } else if (result is Result.Failure) {
                if (result.exception.message.toString() == getString(R.string.no_customer_found_with_these_details)) {
                    authViewModel.signOutUser()
                    navigateToLogin()
                }
            }
        }
    }

    private suspend fun getGarageData(){
        userViewModel.getGarageFromDb(userStorageVM.userId.orEmpty()).collect { result ->
            if (result is Result.Success) {
                userStorageVM.updateGaragePref(result.data)
            } else if (result is Result.Failure) {
                if (result.exception.message.toString() == getString(R.string.no_garage_found_with_these_details)) {
                    authViewModel.signOutUser()
                    navigateToLogin()
                }
            }
        }
    }

    private fun navigateToLogin(){
        navController?.navigate(Screen.LoginScreen.route){
            navController?.graph?.id?.let { popUpTo(it) }
        }
    }
}



