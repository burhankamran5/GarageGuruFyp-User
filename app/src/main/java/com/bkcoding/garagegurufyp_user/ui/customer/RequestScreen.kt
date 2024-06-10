package com.bkcoding.garagegurufyp_user.ui.customer

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.google.gson.Gson

@Composable
fun RequestScreen(navController: NavController, userViewModel: UserViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        userViewModel.getRequest()
    }
    var requestList  = listOf<Request>()
    when (val getRequestResponse = userViewModel.getRequestResponse) {
        Result.Loading -> {
            //isLoading = true
        }

        is Result.Success -> {
            requestList = getRequestResponse.data
        }

        is Result.Failure -> {}
        else -> {}
    }
    RequestScreen(
        requestList = requestList,
        onPostRequestClick = { navController.navigate(Screen.UserRequestForm.route) },
        onRequestClick = { navController.navigate(Screen.RequestBidScreen.route + "/${Uri.encode(Gson().toJson(it))}")}
    )
}

@Composable
private fun RequestScreen(
    requestList: List<Request>?,
    onPostRequestClick: () -> Unit,
    onRequestClick: (Request) -> Unit
){
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Request Screen",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.all_request),
                fontFamily = FontFamily(Font(R.font.neo_sans_bold)),
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Blue,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onPostRequestClick() }
            ) {
                Text(
                    text = stringResource(id = R.string.new_request),
                    fontFamily = FontFamily(Font(R.font.neo_sans_bold)),
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.heightIn(max = 700.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(requestList.orEmpty()){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.orange),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onRequestClick(it) }
                ) {
                    AsyncImage(
                        model = it.images.getOrNull(0),
                        contentDescription = "",
                        modifier = Modifier.aspectRatio(12f / 6f)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = it.carModel,
                            fontFamily = FontFamily(Font(R.font.neo_sans_bold)),
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = it.city,
                            fontFamily = FontFamily(Font(R.font.neo_sans_regular)),
                            color = Color.Gray,
                            textAlign = TextAlign.End
                        )
                    }
                    Text(
                        text = it.description,
                        fontFamily = FontFamily(Font(R.font.neo_sans_bold)),
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RequestScreenPreview() {
    GarageGuruFypUserTheme {
        RequestScreen(requestList = null, onPostRequestClick = {}, onRequestClick = {})
    }
}