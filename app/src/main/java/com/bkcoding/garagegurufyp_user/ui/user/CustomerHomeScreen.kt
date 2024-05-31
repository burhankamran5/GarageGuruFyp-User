package com.bkcoding.garagegurufyp_user.ui.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.ui.AuthViewModel
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM

@Composable
fun CustomerHomeScreen(
    navController: NavController,
    userStorageVM: UserStorageVM = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = Unit) {
        userViewModel.refreshFcmToken()
    }
    Column (modifier = Modifier.fillMaxSize()) {
        Text(text = "Customer Home Screen: ${userStorageVM.getSavedCustomer()?.name}")
        Button(onClick = {
            authViewModel.signOutUser()
            navController.navigate(Screen.LoginScreen.route){
                popUpTo(navController.graph.id)
            }
        }) {
            Text(text = "Logout")
        }
    }
}