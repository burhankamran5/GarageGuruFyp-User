package com.bkcoding.garagegurufyp_user.ui.customer

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.google.gson.Gson

@Composable
fun GarageScreen(navController: NavController, userViewModel: UserViewModel = hiltViewModel()){
    var garageList by rememberSaveable {
        mutableStateOf<List<Garage>?>(null)
    }

    LaunchedEffect(Unit) {
        userViewModel.getGarages()
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

    GarageScreen(
        garageList = garageList,
        onGarageClick = {
            navController.navigate(Screen.GarageDetailsScreen.route + "/${Uri.encode(Gson().toJson(it))}")
        }
    )
}

@Composable
private fun GarageScreen(garageList: List<Garage>?, onGarageClick: (Garage) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bright_gray))
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.garages),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.googlesansbold)),
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(garageList.orEmpty()) { item ->
                GarageCard(modifier = Modifier.clickable { onGarageClick(item) }, item)
            }
        }
    }
}

@Preview
@Composable
fun GarageScreenPreview(){
    GarageGuruFypUserTheme {
        GarageScreen(garageList = null, onGarageClick = {})
    }
}