package com.bkcoding.garagegurufyp_user.ui.customer

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Bid
import com.bkcoding.garagegurufyp_user.dto.BidStatus
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.dto.RequestStatus
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.bkcoding.garagegurufyp_user.ui.theme.Typography
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
        requestList = requestList.filter { it.customer.id == userViewModel.userPreferences.userId },
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
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.bright_gray))) {
        Text(
            fontSize = 22.sp,
            style = Typography.bodyLarge,
            color = Color.Black,
            text = "My Requests",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .background(
                        color = colorResource(id = R.color.orange50),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onPostRequestClick() }
            ) {
                Text(
                    text = stringResource(id = R.string.new_request),
                    style = Typography.bodySmall,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.heightIn(max = 700.dp),
            contentPadding = PaddingValues(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(requestList.orEmpty()){
               RequestItem(request = it, onRequestClick = onRequestClick)
            }
        }
    }
}

@Composable
fun RequestItem(request: Request, onRequestClick: (Request) -> Unit){
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 4.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onRequestClick(request) }
    ) {
        SubcomposeAsyncImage(
            contentScale = ContentScale.FillBounds,
            loading = {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.bright_gray)))
            },
            model = request.images.getOrNull(0),
            contentDescription = "",
            modifier = Modifier
                .aspectRatio(12f / 6f)
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .clip(
                    shape = RoundedCornerShape(12.dp)
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = request.carModel.uppercase(),
                style = Typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Start,
            )
            Text(
                text = request.city,
                style = Typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.End
            )
        }
        Text(
            text = request.description,
            style = Typography.labelSmall,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 18.dp, top = 4.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()        )
    }
}

@Preview
@Composable
fun PreviewRequestItem(){
    val request = Request(
        images = listOf("https://firebasestorage.googleapis.com/v0/b/garageguru-4528e.appspot.com/o/Request%20Images%2F-O-71rg_-t9UGk_4SB30?alt=media&token=bd0270be-ac48-4f48-bcc1-5e733929ac31"),
        carModel = "honda City R",
        description = "description",
        city = "Lahore",
        bids = mapOf(
            "bidder1" to Bid(bidStatus = BidStatus.PENDING, id = "1",  price = "100.00", garage = Garage(name = "Pak Wheel", city = "Lahore")),
            "bidder2" to Bid(bidStatus = BidStatus.PENDING, id = "1",  price = "100.00", garage = Garage(name = "Pak Wheel", city = "Lahore"))
        ),
//        acceptedBid = Bid(bidStatus = BidStatus.ACCEPTED, id = "1",  price = "100.00", garage = Garage(name = "Pak Wheel", city = "Lahore")),
        status = RequestStatus.COMPLETED,
        rating = 3,
        review = "It was an amazing experience with the garage. Delivered amazing"
    )
    RequestItem(request = request) {
        
    }
}


@Preview(showBackground = true)
@Composable
fun RequestScreenPreview() {
    GarageGuruFypUserTheme {
        RequestScreen(requestList = null, onPostRequestClick = {}, onRequestClick = {})
    }
}