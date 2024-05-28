package com.bkcoding.garagegurufyp_user.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.navigation.NamedNavArgument
import com.bkcoding.garagegurufyp_user.dto.BottomNavigationItem

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object OnBoarding : Screen("OnBoardingScreen")
    data object LoginScreen : Screen("LoginScreen")
    data object ChooseSignUpScreen : Screen("ChooseSignUp")
    data object UserSignUpScreen : Screen("UserSignUpScreen")
    data object GarageSignUpScreen : Screen("GarageSignUpScreen")
    data object VerifyOtpScreen : Screen("VerifyOtpScreen")
    data object SignUpConfirmationScreen : Screen("SignUpConfirmationScreen")
    data object CustomerHomeScreen : Screen("CustomerHomeScreen")
    data object GarageHomeScreen : Screen("GarageHomeScreen")
}

val BOTTOM_MENU_LIST = listOf(
    BottomNavigationItem(
        title = "Home",
        route = Screen.CustomerHomeScreen.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Menu,
    ),
    BottomNavigationItem(
        title = "Register",
        route = Screen.CustomerHomeScreen.route,
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email
    ),
    BottomNavigationItem(
        title = "Details",
        route = Screen.CustomerHomeScreen.route,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    ),
)

enum class ScreenType(val label: String){
    LOGIN_SCREEN("LoginScreen"),
    ONBOARDING_SCREEN("OnBoardingScreen"),
    CUSTOMER_HOME_SCREEN("CustomerHomeScreen"),
    GARAGE_HOME_SCREEN("GarageHomeScreen")

}
