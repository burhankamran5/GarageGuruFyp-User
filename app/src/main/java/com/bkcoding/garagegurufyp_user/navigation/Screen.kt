package com.bkcoding.garagegurufyp_user.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object OnBoarding : Screen("OnBoardingScreen")
    data object SplashScreen : Screen("SplashScreen")
    data object LoginScreen : Screen("LoginScreen")
    data object ChooseSignUpScreen : Screen("ChooseSignUp")
    data object UserSignUpScreen : Screen("UserSignUpScreen")
    data object GarageSignUpScreen : Screen("GarageSignUpScreen")
    data object VerifyOtpScreen : Screen("VerifyOtpScreen")
    data object SignUpConfirmationScreen : Screen("SignUpConfirmationScreen")
    data object CustomerHomeScreen : Screen("CustomerHomeScreen")
    data object GarageHomeScreen : Screen("GarageHomeScreen")
}