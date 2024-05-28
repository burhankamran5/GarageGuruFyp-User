package com.bkcoding.garagegurufyp_user.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
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
    data object UserHomeScreen : Screen("UserHomeScreen")
}

val BOTTOM_MENU_LIST = listOf(
    BottomNavigationItem(
        title = "Home",
        route = Screen.UserHomeScreen.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Menu,
    ),
    BottomNavigationItem(
        title = "Register",
        route = Screen.VerifyOtpScreen.route,
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email
    ),
    BottomNavigationItem(
        title = "Details",
        route = Screen.LoginScreen.route,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    ),
)
