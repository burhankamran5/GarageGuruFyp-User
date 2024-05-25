package com.bkcoding.garagegurufyp_user.ui.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.ui.AuthViewModel
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM

@Composable
fun UserHomeScreen(
    navController: NavController,
    userStorageVM: UserStorageVM = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
){
    Column (modifier = Modifier.fillMaxSize()) {
        Text(text = "User Home Screen")
        Button(onClick = {
            authViewModel.signOutUser()
            navController.navigate(Screen.LoginScreen.route)
        }) {
            Text(text = "Logout")
        }
    }
}