package com.bkcoding.garagegurufyp_user.ui.customer

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.bkcoding.garagegurufyp_user.ui.theme.Typography
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CustomerHomeScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
){
    val context = LocalContext.current
    var garageList by rememberSaveable {
        mutableStateOf<List<Garage>?>(null)
    }

    LaunchedEffect(key1 = Unit) {
        userViewModel.refreshFcmToken()
        userViewModel.getGarages()
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

    when (val homeUiState = userViewModel.homeScreenUIState) {
        Result.Loading -> {
            //isLoading = true
            Log.i("TAG", "GarageScreen: loading")
        }

        is Result.Success -> {
            garageList = homeUiState.data
        }

        is Result.Failure -> {}
        else -> {}
    }

    CustomerHomeScreen(
        garageList = garageList,
        onGarageClick = {
            val conversation = Conversation(
                seen = false,
                createdAt = null,
                userId = it.id,
                userName = it.name,
                profileImage = it.images.getOrNull(0).orEmpty()
            )
            navController.navigate(Screen.ChatScreen.route + "/${Uri.encode(Gson().toJson(conversation))}")
        }
    )
}

@Composable
private fun CustomerHomeScreen(garageList: List<Garage>?, onGarageClick: (Garage) -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = "Welcome",
                style = Typography.bodyLarge,
                textAlign = TextAlign.Start,
                color = Color.Black
            )
            Text(
                text = "customerName",
                style = Typography.titleLarge,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        AutoSwipeViewPager(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Car Cries,Guru Replies",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.googlesansbold)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = stringResource(id = R.string.garage),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                lineHeight = 15.sp,
                color = Color.Black
            )
            Text(
                text = stringResource(id = R.string.view_all),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                lineHeight = 15.sp,
                color = Color.Black
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 800.dp),
            contentPadding = PaddingValues(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(garageList.orEmpty()) { item ->
                GarageCard(modifier = Modifier.clickable { onGarageClick(item) }, item)
            }
        }
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
                color = colorResource(id = R.color.orange50), shape = RoundedCornerShape(10.dp)
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
private fun GarageCard(modifier: Modifier = Modifier, garage: Garage) {
    Box(
        modifier = modifier.background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)) {
            AsyncImage(
                model = garage.images.getOrNull(0),
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(0.dp)
                    .width(150.dp)
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            )
            Text(
                text = garage.name,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppinssemibold)),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = garage.location,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.googlesansregular)),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = garage.phoneNumber,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.googlesansregular)),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomerHomeScreenPreview() {
    GarageGuruFypUserTheme {
        CustomerHomeScreen(garageList = emptyList(), onGarageClick = {})
    }
}