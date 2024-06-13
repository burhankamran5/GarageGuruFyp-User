package com.bkcoding.garagegurufyp_user.ui.garage

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    val context = LocalContext.current
    GarageHomeScreen(
        loginGarageInfo = userStorageVM.userPreferences.getGarage(),
        onMenuClick = {
            when (it) {
                context.getString(R.string.request) -> navController.navigate(Screen.GarageRequestScreen.route)
                context.getString(R.string.inbox) -> navController.navigate(Screen.ConversationsScreen.route)
                context.getString(R.string.my_request) -> navController.navigate(Screen.MyRequestScreen.route)
            }
        }
    )
}

@Composable
private fun GarageHomeScreen(loginGarageInfo: Garage?, onMenuClick: (String) -> Unit) {
    val menuList = listOf(
        stringResource(id = R.string.request),
        stringResource(id = R.string.inbox),
        stringResource(id = R.string.my_request)
    )
    val iconList = listOf(R.drawable.ic_request_garage, R.drawable.ic_inbox, R.drawable.ic_inbox)
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
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .heightIn(max = 700.dp)
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                ),
            contentPadding = PaddingValues(top = 20.dp),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            itemsIndexed(menuList) { index, item ->
                MenuCard(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable { onMenuClick(item) },
                    text = item,
                    id = iconList[index]
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
        GarageHomeScreen(loginGarageInfo = null, onMenuClick = {})
    }
}