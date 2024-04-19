package com.bkcoding.garagegurufyp_user.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bkcoding.garagegurufyp_user.ui.login.LoginScreen
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.onboarding.OnBoardingScreen
import com.bkcoding.garagegurufyp_user.ui.signup.ChooseSignUp
import com.bkcoding.garagegurufyp_user.ui.signup.GarageSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.SignUpConfirmationScreen
import com.bkcoding.garagegurufyp_user.ui.signup.UserSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.VerifyOtpScreen

@Composable
fun Navigation() {
    val userStorageVM: UserStorageVM = hiltViewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (userStorageVM.isFirstLaunch()) Screen.OnBoarding.route else Screen.LoginScreen.route
    ) {
        composable(Screen.OnBoarding.route){
            OnBoardingScreen(navController)
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(Screen.ChooseSignUpScreen.route){
            ChooseSignUp(navController)
        }
        composable(Screen.UserSignUpScreen.route){
            UserSignUpScreen(navController)
        }
        composable(Screen.GarageSignUpScreen.route){
            GarageSignUpScreen(navController)
        }

        composable(Screen.VerifyOtpScreen.route){
            VerifyOtpScreen(navController)
        }

        composable(Screen.SignUpConfirmationScreen.route){
            SignUpConfirmationScreen()
        }
    }
}

