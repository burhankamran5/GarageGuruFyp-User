package com.bkcoding.garagegurufyp_user.ui.customer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.BottomNavigationItem
import com.bkcoding.garagegurufyp_user.navigation.BOTTOM_MENU_LIST

@Composable
fun GarageBottomNavigationBar(
    selectedDestination: String,
    navigateToTopLevelDestination: (BottomNavigationItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = colorResource(id = R.color.orange).copy(alpha = 0.7f)
    ) {
        BOTTOM_MENU_LIST.forEach { garageBottomDestination ->
            NavigationBarItem(
                selected = selectedDestination == garageBottomDestination.route,
                onClick = { navigateToTopLevelDestination(garageBottomDestination) },
                icon = {
                    Icon(
                        painter = painterResource(id = garageBottomDestination.selectedIcon),
                        contentDescription = ""
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.orange),
                    selectedTextColor = colorResource(id = R.color.orange),
                    unselectedIconColor = colorResource(id = R.color.white),
                    unselectedTextColor = colorResource(id = R.color.white),
                    disabledIconColor = colorResource(id = R.color.white),
                    disabledTextColor = colorResource(id = R.color.white)
                )
            )
        }
    }
}

