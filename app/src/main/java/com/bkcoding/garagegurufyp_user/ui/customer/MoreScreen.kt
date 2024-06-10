package com.bkcoding.garagegurufyp_user.ui.customer

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme

@Composable
fun MoreScreen(userViewModel: UserViewModel = hiltViewModel()){
    val activity = LocalContext.current as Activity
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "More Screen",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(50.dp)
        )
        Text(
            text = "Log Out",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
                .clickable {
                    userViewModel.userPreferences.signOut()
                    activity.finish()
                }
        )
    }
}

@Preview
@Composable
fun MoreScreenPreview(){
    GarageGuruFypUserTheme {
        MoreScreen()
    }
}