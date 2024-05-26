package com.bkcoding.garagegurufyp_user.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.ui.garage.GarageHomeScreen
import com.bkcoding.garagegurufyp_user.ui.login.LoginScreen
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.onboarding.OnBoardingScreen
import com.bkcoding.garagegurufyp_user.ui.signup.ChooseSignUp
import com.bkcoding.garagegurufyp_user.ui.signup.GarageSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.SignUpConfirmationScreen
import com.bkcoding.garagegurufyp_user.ui.signup.UserSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.VerifyOtpScreen
import com.bkcoding.garagegurufyp_user.ui.user.CustomerHomeScreen

@Composable
fun Navigation(
    userStorageVM: UserStorageVM,
    navController: NavHostController,
    startDestination: String,
) {


    NavHost(
        navController = navController,
        startDestination = startDestination

    ) {
        composable(Screen.OnBoarding.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            OnBoardingScreen(navController)
        }

        composable(Screen.LoginScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None } ) {
            LoginScreen(navController = navController, userStorageVM = userStorageVM)
        }

        composable(Screen.ChooseSignUpScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            ChooseSignUp(navController)
        }

        composable(Screen.UserSignUpScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            UserSignUpScreen(navController, onChangeUser = { userStorageVM.customer = it })
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
            VerifyOtpScreen(navController, userStorageVM.customer, userStorageVM.garage )
        }

        composable(Screen.SignUpConfirmationScreen.route + "/{isGarage}",
            arguments = listOf(navArgument("isGarage") { type = NavType.BoolType }),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {backStackEntry ->
            SignUpConfirmationScreen(navController, backStackEntry.arguments?.getBoolean("isGarage") ?: false)
        }

        composable(
            route = Screen.CustomerHomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            CustomerHomeScreen(navController = navController, userStorageVM = userStorageVM)
        }

        composable(
            route = Screen.GarageHomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            GarageHomeScreen(navController = navController, userStorageVM = userStorageVM)
        }

    }
}

