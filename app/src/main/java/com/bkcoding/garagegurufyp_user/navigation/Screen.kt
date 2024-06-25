package com.bkcoding.garagegurufyp_user.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.bkcoding.garagegurufyp_user.R
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
    data object RequestScreen : Screen("RequestScreen")
    data object GarageScreen : Screen("GarageScreen")
    data object ChatScreen : Screen("ChatScreen")
    data object ConversationsScreen : Screen("ConversationsScreen")
    data object MoreScreen : Screen("MoreScreen")
    data object UserRequestForm : Screen("UserRequestForm")
    data object GarageRequestScreen : Screen("GarageRequestScreen")
    data object RequestBidScreen : Screen("RequestBidScreen")
    data object MyRequestScreen : Screen("MyRequestScreen")
    data object NotificationScreen : Screen("NotificationScreen")
    data object GarageDetailsScreen : Screen("GarageDetailsScreen")
    data object AboutUsScreen : Screen("AboutUsScreen")
}

class GarageNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: BottomNavigationItem) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

val BOTTOM_MENU_LIST = listOf(
    BottomNavigationItem(
        title = "Home",
        route = Screen.CustomerHomeScreen.route,
        selectedIcon = R.drawable.ic_home,
        unselectedIcon = R.drawable.ic_home
    ),
    BottomNavigationItem(
        title = "Request",
        route = Screen.RequestScreen.route,
        selectedIcon = R.drawable.ic_request,
        unselectedIcon = R.drawable.ic_request
    ),
    BottomNavigationItem(
        title = "Garage",
        route = Screen.GarageScreen.route,
        selectedIcon = R.drawable.ic_garage,
        unselectedIcon = R.drawable.ic_garage
    ),
    BottomNavigationItem(
        title = "Chat",
        route = Screen.ConversationsScreen.route,
        selectedIcon = R.drawable.ic_chat,
        unselectedIcon = R.drawable.ic_chat
    ),
    BottomNavigationItem(
        title = "More",
        route = Screen.MoreScreen.route,
        selectedIcon = R.drawable.ic_more,
        unselectedIcon = R.drawable.ic_more
    )
)