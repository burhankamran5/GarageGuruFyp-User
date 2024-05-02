package com.bkcoding.garagegurufyp_user.ui.home



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bkcoding.garagegurufyp_user.navigation.BOTTOM_MENU_LIST

@Composable
fun MobileScaffold(
    navController: NavHostController,
    content: @Composable (modifier: Modifier) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationScreen(
                navController = navController
            )
        }
    ) {
        content(Modifier.padding(it))
    }
}


@Composable
private fun BottomNavigationScreen(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.inversePrimary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        BOTTOM_MENU_LIST.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(screen.selectedIcon, contentDescription = null) },
                label = { Text(screen.title) }
            )
        }
    }
}