package com.bkcoding.garagegurufyp_user.navigation

import UserHomeScreen
import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.User
import com.bkcoding.garagegurufyp_user.ui.home.MobileScaffold
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
fun GarageApp(navController: NavHostController, isGoToHomeScreen: Boolean,goToHomeScreen: () -> Unit) {
    if (isGoToHomeScreen) {
        MobileScaffold(navController = navController) {
            BottomNavHost(navController)
        }
    } else {
        Navigation(navController){
            goToHomeScreen()
        }
    }
}




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation(navController: NavHostController, goToHomeScreen: () -> Unit) {
    val userStorageVM: UserStorageVM = hiltViewModel()
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
            VerifyOtpScreen(navController, userStorageVM.user, userStorageVM.garage ){
                goToHomeScreen()
            }
        }

        composable(Screen.SignUpConfirmationScreen.route + "/{isGarage}",
            arguments = listOf(navArgument("isGarage") { type = NavType.BoolType }),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {backStackEntry ->
            SignUpConfirmationScreen(navController, backStackEntry.arguments?.getBoolean("isGarage") ?: false)
        }
    }
}

@Composable
fun BottomNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(Screen.UserHomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            UserHomeScreen(navController)
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

        composable(
            route = Screen.VerifyOtpScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            VerifyOtpScreen(navController, user = User(), garage = Garage()){}
        }
        composable(
            route = Screen.LoginScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            LoginScreen(navController)
        }
    }
}


