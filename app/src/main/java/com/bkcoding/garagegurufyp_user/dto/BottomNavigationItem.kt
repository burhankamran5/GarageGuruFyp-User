package com.bkcoding.garagegurufyp_user.dto

import androidx.annotation.DrawableRes

data class BottomNavigationItem(
    val title: String,
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
)