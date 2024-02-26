package com.bkcoding.garagegurufyp_user.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bkcoding.garagegurufyp_user.ui.login.LoginScreen
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.onboarding.OnBoardingScreen
import com.bkcoding.garagegurufyp_user.ui.signup.AdditionalSignUpFields
import com.bkcoding.garagegurufyp_user.ui.signup.ChooseSignUp
import com.bkcoding.garagegurufyp_user.ui.signup.GarageSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.UserSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.VerifyOtpScreen

@Composable
fun Navigation() {
    val userStorageVM: UserStorageVM = hiltViewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (userStorageVM.isFirstLaunch()) "OnBoardingScreen" else "LoginScreen"
    ) {
        composable("OnBoardingScreen") {
            OnBoardingScreen(navController)
        }
        composable("LoginScreen") {
            LoginScreen(navController)
        }
        composable("ChooseSignUp") {
            ChooseSignUp(navController)
        }
        composable("UserSignUpScreen") {
            UserSignUpScreen(navController)
        }
        composable("GarageSignUpScreen") {
            GarageSignUpScreen()
        }

        composable("AdditionalSignUpFields") {
            AdditionalSignUpFields()
        }
        composable("VerifyOtpScreen") {
            VerifyOtpScreen()
        }
    }
}

