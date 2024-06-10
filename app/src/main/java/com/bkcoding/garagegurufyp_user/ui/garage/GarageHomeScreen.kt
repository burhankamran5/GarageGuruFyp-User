package com.bkcoding.garagegurufyp_user.ui.garage

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.ui.AuthViewModel
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme

@Composable
fun GarageHomeScreen(
    navController: NavController,
    userStorageVM: UserStorageVM = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
){
    GarageHomeScreen(
        loginGarageInfo = userStorageVM.userPreferences.getGarage(),
        onInboxClick = { navController.navigate(Screen.ConversationsScreen.route) },
        onRequestClick = {
            navController.navigate(Screen.GarageRequestScreen.route)
        }
    )
}

@Composable
private fun GarageHomeScreen(loginGarageInfo: Garage?, onInboxClick: () -> Unit, onRequestClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.orange50))
    ) {
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = loginGarageInfo?.images?.getOrNull(0),
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                error = painterResource(id = R.drawable.ic_placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = loginGarageInfo?.name ?: "Garage Name",
                fontSize = 17.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MenuCard(
                    modifier = Modifier.clickable { onRequestClick() },
                    text = "Request",
                    id = R.drawable.ic_request_garage
                )
                MenuCard(
                    modifier = Modifier.clickable { onInboxClick() },
                    text = "Inbox",
                    id = R.drawable.ic_inbox
                )
            }
        }
    }
}

@Composable
private fun MenuCard(modifier: Modifier = Modifier, text: String, @DrawableRes id: Int) {
    Column(
        modifier = modifier
            .border(width = 0.5.dp, color = Color.LightGray, RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = "",
            modifier = Modifier
                .size(120.dp)
                .padding(10.dp)
        )
        Text(
            text = text,
            fontSize = 17.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}

@Preview
@Composable
fun GarageHomeScreenPreview() {
    GarageGuruFypUserTheme {
        GarageHomeScreen(loginGarageInfo = null, onInboxClick = {}, onRequestClick = {})
    }
}