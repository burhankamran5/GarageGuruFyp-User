package com.bkcoding.garagegurufyp_user.ui.user

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.ui.AuthViewModel
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CustomerHomeScreen(
    navController: NavController,
    userStorageVM: UserStorageVM = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onLogOut: () -> Unit
){
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        userViewModel.refreshFcmToken()
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            context.showToast("Permission Granted!")
        } else {
            context.showToast("Permission denied!")
        }
    }
    LaunchedEffect(key1 = Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    CustomerHomeScreen()
}

@Composable
private fun CustomerHomeScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Welcome,Burhan Kamran",
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(30.dp))
        AutoSwipeViewPager(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Car Cries,Guru Replies",
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        ItemList()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSwipeViewPager(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    val imageList = listOf(R.drawable.caricon2, R.drawable.chat, R.drawable.mechanic_icon_com)

    Box(
        modifier = modifier
            .fillMaxWidth(.6f)
            .height(220.dp)
            .padding(horizontal = 8.dp)
            .background(
                color = colorResource(id = R.color.orange50),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = imageList[page]),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(8.dp) // Add padding for images
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        LaunchedEffect(pagerState.currentPage) {
            while (true) {
                delay(3000) // Adjust swipe interval as needed
                coroutineScope.launch {
                    pagerState.animateScrollToPage((pagerState.currentPage + 1) % imageList.size)
                }
            }
        }
    }
}

@Composable
fun ItemList() {
    val titleList = listOf("Request", "View Garages", "Bids", "Chat")

    LazyVerticalGrid(
        modifier = Modifier.heightIn(max = 800.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(titleList) {
            ItemDesign(title = it)
        }
    }
}

@Composable
fun ItemDesign(modifier: Modifier = Modifier, title: String) {
    Box(
        modifier = modifier
            .size(130.dp)
            .clickable { }
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Box(modifier = Modifier.matchParentSize()) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(5.dp)
            )
        }
    }
}

@Preview
@Composable
fun CustomerHomeScreenPreview() {
    GarageGuruFypUserTheme {
        CustomerHomeScreen()
    }
}