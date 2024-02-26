package com.bkcoding.garagegurufyp_user.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bkcoding.garagegurufyp_user.ui.login.LoginScreen
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.onboarding.OnBoardingScreen
import com.bkcoding.garagegurufyp_user.ui.signup.SignUpScreen

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
        composable("SignUpScreen") {
            SignUpScreen()
        }
    }
}

