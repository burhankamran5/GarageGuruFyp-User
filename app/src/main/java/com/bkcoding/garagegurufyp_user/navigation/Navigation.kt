package com.bkcoding.garagegurufyp_user.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bkcoding.garagegurufyp_user.R
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
        composable(Screen.OnBoarding.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            OnBoardingScreen(navController)
        }
        composable(Screen.LoginScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None } ) {
            LoginScreen(navController)
        }
        composable(Screen.ChooseSignUpScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            ChooseSignUp(navController)
        }
        composable(Screen.UserSignUpScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            UserSignUpScreen(navController, onChangeUser = { userStorageVM.user = it })
        }
        composable(Screen.GarageSignUpScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            GarageSignUpScreen(navController, onChangeGarage = {userStorageVM.garage = it})
        }

        composable(
            route = Screen.VerifyOtpScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None })
        {
            VerifyOtpScreen(navController, userStorageVM.user, userStorageVM.garage )
        }

        composable(Screen.SignUpConfirmationScreen.route + "/{isGarage}",
            arguments = listOf(navArgument("isGarage") { type = NavType.BoolType }),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {backStackEntry ->
            SignUpConfirmationScreen(navController, backStackEntry.arguments?.getBoolean("isGarage") ?: false)
        }

    }
}

