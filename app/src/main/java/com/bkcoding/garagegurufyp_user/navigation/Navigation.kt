package com.bkcoding.garagegurufyp_user.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.ui.chat.ChatScreen
import com.bkcoding.garagegurufyp_user.ui.customer.AboutUsScreen
import com.bkcoding.garagegurufyp_user.ui.customer.ConversationsScreen
import com.bkcoding.garagegurufyp_user.ui.customer.CustomerHomeScreen
import com.bkcoding.garagegurufyp_user.ui.customer.GarageDetailsScreen
import com.bkcoding.garagegurufyp_user.ui.customer.MoreScreen
import com.bkcoding.garagegurufyp_user.ui.customer.RequestDetailsScreen
import com.bkcoding.garagegurufyp_user.ui.customer.RequestScreen
import com.bkcoding.garagegurufyp_user.ui.garage.GarageHomeScreen
import com.bkcoding.garagegurufyp_user.ui.garage.GarageRequestScreen
import com.bkcoding.garagegurufyp_user.ui.garage.MyRequestScreen
import com.bkcoding.garagegurufyp_user.ui.garage.NotificationScreen
import com.bkcoding.garagegurufyp_user.ui.customer.GarageBottomNavigationBar
import com.bkcoding.garagegurufyp_user.ui.login.LoginScreen
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.onboarding.OnBoardingScreen
import com.bkcoding.garagegurufyp_user.ui.request.UserRequestForm
import com.bkcoding.garagegurufyp_user.ui.signup.ChooseSignUp
import com.bkcoding.garagegurufyp_user.ui.signup.GarageSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.SignUpConfirmationScreen
import com.bkcoding.garagegurufyp_user.ui.signup.UserSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.VerifyOtpScreen
import com.bkcoding.garagegurufyp_user.ui.customer.GarageScreen
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GarageNavigation(
    navController: NavHostController,
    userStorageVM: UserStorageVM,
    startDestination: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: Screen.LoginScreen.route
    val navigationActions = remember(navController) {
        GarageNavigationActions(navController)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Navigation(
            Modifier.weight(1f),
            userStorageVM,
            navController,
            startDestination
        )
        if (BOTTOM_MENU_LIST.any { it.route == navController.currentDestination?.route } && navController.previousBackStackEntry?.destination?.route != Screen.GarageHomeScreen.route) {
            GarageBottomNavigationBar(
                selectedDestination = selectedDestination,
                navigateToTopLevelDestination = navigationActions::navigateTo
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    userStorageVM: UserStorageVM,
    navController: NavHostController,
    startDestination: String
) {

    NavHost(
        modifier = modifier,
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

        //garage
        composable(
            route = Screen.GarageHomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            GarageHomeScreen(navController = navController, userStorageVM = userStorageVM)
        }

        composable(
            route = Screen.GarageRequestScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            GarageRequestScreen(navController = navController)
        }

        composable(
            route = Screen.ConversationsScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            ConversationsScreen(navController)
        }

        composable(
            route = Screen.MyRequestScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            MyRequestScreen(navController)
        }

        composable(
            route = Screen.NotificationScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            NotificationScreen(navController)
        }

        composable(
            route = Screen.CustomerHomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            CustomerHomeScreen(navController = navController)
        }

        composable(
            route = Screen.RequestScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            RequestScreen(navController = navController)
        }

        composable(
            route = Screen.UserRequestForm.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            UserRequestForm(navController = navController)
        }

        composable(
            route = Screen.GarageScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            GarageScreen(navController)
        }

        composable(
            route = Screen.ConversationsScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            ConversationsScreen(navController)
        }

        composable(
            arguments = listOf(navArgument("conversation") { type = NavType.StringType }),
            route = Screen.ChatScreen.route + "/{conversation}",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            val conversation = it.arguments?.getString("conversation")
            val conversationResponse = conversation?.let {data->
                Gson().fromJson(data, Conversation::class.java)
            }
            ChatScreen(navController, conversationResponse)
        }

        composable(
            route = Screen.MoreScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            MoreScreen(navController = navController)
        }

        composable(
            route = Screen.AboutUsScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            AboutUsScreen(navController = navController)
        }

        composable(
            arguments = listOf(navArgument("request") { type = NavType.StringType }),
            route = Screen.RequestBidScreen.route + "/{request}",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            val request = it.arguments?.getString("request")
            val requestResponse = request?.let {data->
                Gson().fromJson(data, Request::class.java)
            }
            RequestDetailsScreen(navController = navController, request = requestResponse)
        }

        composable(
            arguments = listOf(navArgument("garage") { type = NavType.StringType }),
            route = Screen.GarageDetailsScreen.route + "/{garage}",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            val garage = it.arguments?.getString("garage")
            val garageResponse = garage?.let {data->
                Gson().fromJson(data, Garage::class.java)
            }
            GarageDetailsScreen(navController = navController, garage = garageResponse)
        }

    }
}


