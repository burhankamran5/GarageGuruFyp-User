package com.bkcoding.garagegurufyp_user.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.ui.chat.ChatScreen
import com.bkcoding.garagegurufyp_user.ui.garage.GarageHomeScreen
import com.bkcoding.garagegurufyp_user.ui.home.MobileScaffold
import com.bkcoding.garagegurufyp_user.ui.login.LoginScreen
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.onboarding.OnBoardingScreen
import com.bkcoding.garagegurufyp_user.ui.signup.ChooseSignUp
import com.bkcoding.garagegurufyp_user.ui.signup.GarageSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.SignUpConfirmationScreen
import com.bkcoding.garagegurufyp_user.ui.signup.UserSignUpScreen
import com.bkcoding.garagegurufyp_user.ui.signup.VerifyOtpScreen
import com.bkcoding.garagegurufyp_user.ui.customer.ConversationsScreen
import com.bkcoding.garagegurufyp_user.ui.customer.CustomerHomeScreen
import com.bkcoding.garagegurufyp_user.ui.user.GarageScreen
import com.bkcoding.garagegurufyp_user.ui.user.MoreScreen
import com.bkcoding.garagegurufyp_user.ui.user.RequestScreen
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GarageNavigation(
    navController: NavHostController,
    userStorageVM: UserStorageVM,
    startDestination: String
) {
    var goToCustomerHomeScreen by rememberSaveable { mutableStateOf(startDestination == ScreenType.CUSTOMER_HOME_SCREEN.label) }
    if(goToCustomerHomeScreen){
        MobileScaffold(navController = navController) {
            BottomNavHost(navController, userStorageVM){
                goToCustomerHomeScreen = false
            }
        }
    }else{
        Navigation(userStorageVM, navController, startDestination){
            goToCustomerHomeScreen = true
        }
    }
}




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation(
    userStorageVM: UserStorageVM,
    navController: NavHostController,
    startDestination: String,
    goToCustomerScreen: () -> Unit
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
            LoginScreen(navController = navController, userStorageVM = userStorageVM){
                goToCustomerScreen()
            }
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
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun BottomNavHost(
    navController: NavHostController,
    userStorageVM: UserStorageVM,
    onLogout: () -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.CustomerHomeScreen.route) {

        composable(
            route = Screen.CustomerHomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            CustomerHomeScreen(navController = navController, userStorageVM = userStorageVM){
                onLogout()
            }
        }

        composable(
            route = Screen.RequestScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            RequestScreen()
        }

        composable(
            route = Screen.GarageScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ){
            GarageScreen()
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
            MoreScreen()
        }
    }
}


