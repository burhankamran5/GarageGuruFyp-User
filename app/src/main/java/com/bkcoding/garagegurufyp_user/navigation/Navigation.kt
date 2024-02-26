package com.bkcoding.garagegurufyp_user.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
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
import com.bkcoding.garagegurufyp_user.ui.signup.AdditionalSignUpFields
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
        startDestination = if (userStorageVM.isFirstLaunch()) "OnBoardingScreen" else "LoginScreen"
    ) {
        composable("OnBoardingScreen",
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 },   animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing)) },
            exitTransition = {slideOutHorizontally(targetOffsetX = { it / 2 },  animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing))}) {
            OnBoardingScreen(navController)
        }
        composable("LoginScreen",
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 },   animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing)) },
            exitTransition = {slideOutHorizontally(targetOffsetX = { it / 2 },  animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing))}) {
            LoginScreen(navController)
        }
        composable("ChooseSignUp",
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 },   animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing)) },
            exitTransition = {slideOutHorizontally(targetOffsetX = { it / 2 },  animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing))}) {
            ChooseSignUp(navController)
        }
        composable("UserSignUpScreen",
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 },   animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing)) },
            exitTransition = {slideOutHorizontally(targetOffsetX = { it / 2 },  animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing))}) {
            UserSignUpScreen(navController
            )
        }
        composable("GarageSignUpScreen",
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 },   animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing)) },
            exitTransition = {slideOutHorizontally(targetOffsetX = { it / 2 },  animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing))}) {
            GarageSignUpScreen(navController)
        }

        composable("AdditionalSignUpFields",
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 },   animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing)) },
            exitTransition = {slideOutHorizontally(targetOffsetX = { it / 2 },  animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing))}) {
            AdditionalSignUpFields()
        }
        composable("VerifyOtpScreen",
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 },   animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing)) },
            exitTransition = {slideOutHorizontally(targetOffsetX = { it / 2 },  animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing))}) {
            VerifyOtpScreen(navController)
        }

        composable("SignUpConfirmationScreen",
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 },   animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing)) },
            exitTransition = {slideOutHorizontally(targetOffsetX = { it / 2 },  animationSpec = tween(durationMillis = 700, easing =  FastOutSlowInEasing))}) {
            SignUpConfirmationScreen()
        }



    }
}

