package com.bkcoding.garagegurufyp_user.ui.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Bid
import com.bkcoding.garagegurufyp_user.dto.BidStatus
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.NotificationAction
import com.bkcoding.garagegurufyp_user.dto.NotificationData
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.dto.RequestStatus
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.repository.fcm.Message
import com.bkcoding.garagegurufyp_user.repository.fcm.Notification
import com.bkcoding.garagegurufyp_user.repository.fcm.NotificationReq
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.bkcoding.garagegurufyp_user.ui.theme.Typography
import com.google.firebase.database.ServerValue
import kotlinx.coroutines.launch

@Composable
fun RequestDetailsScreen(
    navController: NavController,
    request: Request?,
    userViewModel: UserViewModel = hiltViewModel()
) {
    var latestRequest by remember { mutableStateOf(request) }
    val scope = rememberCoroutineScope()
    RequestDetailsScreen(
        request = latestRequest,
        onBackPress = { navController.popBackStack() },
        onRequestUpdated = {
            scope.launch {
                userViewModel.updateRequest(it).collect{ result ->
                    if (result is Result.Success){
                        userViewModel.sendPushNotification(
                            NotificationReq(
                                Message(
                                    token = it.assignedGarage?.token.orEmpty(),
                                    notification = Notification(title = "Accept", body = "${it.customer.name} Accept your Request!")
                                )
                            )
                        )
                        userViewModel.addPushNotificationOnDB(
                            NotificationData(
                                from = it.customer.id,
                                to = it.assignedGarage?.id.orEmpty(),
                                thumbnail = it.images.getOrNull(0).orEmpty(),
                                title = NotificationAction.BID_ACCEPTED.label,
                                description = "${it.customer.name} Accept your Request!",
                                sentAt = ServerValue.TIMESTAMP
                            )
                        )
                        latestRequest = it
                    }
                }
            }
        }
    )
}

@Composable
private fun RequestDetailsScreen(request: Request?, onBackPress: () -> Unit, onRequestUpdated: (Request) -> Unit) {
    request ?: return
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.9f)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable { onBackPress() }
            )
            Text(
                text = "Garage's Offers",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        SubcomposeAsyncImage(
            model = request.images.getOrNull(0),
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 250.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = request.carModel,
                style = Typography.bodySmall,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
            Text(
                text = request.city,
                style = Typography.labelSmall,
                color = Color.Gray,
                textAlign = TextAlign.End
            )
        }
        Text(
            text = request.description,
            style = Typography.labelSmall,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (request.acceptedBid != null){
            Text(
                text = stringResource(id = R.string.accepted_bid),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .align(Alignment.Start)
            )
            BidItem(bid = request.acceptedBid, onBidAction = {})
        }
        val bids = request.bids.toList().map { it.second }.filter { it.bidStatus == BidStatus.PENDING}
        if (bids.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.all_bids),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .align(Alignment.Start)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 700.dp),
                contentPadding = PaddingValues(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(bids) { bid ->
                    BidItem(modifier = Modifier, bid = bid) { accepted ->
                        if (accepted) {
                            val acceptedBid = bid.copy(bidStatus = BidStatus.ACCEPTED)
                            val updatedBids = request.bids.toMutableMap()
                            updatedBids[bid.id] = acceptedBid
                            onRequestUpdated(request.copy(bids = updatedBids, acceptedBid = acceptedBid, status = RequestStatus.IN_PROGRESS))
                        } else {
                            val rejectedBid = bid.copy(bidStatus = BidStatus.DECLINED)
                            val updatedBids = request.bids.toMutableMap()
                            updatedBids[bid.id] = rejectedBid
                            onRequestUpdated(request.copy(bids = updatedBids))
                        }
                    }
                }
            }
        }
        if (request.status == RequestStatus.COMPLETED){
            if (request.review == null) {
                ReviewInputCard { rating, review ->
                    onRequestUpdated(request.copy(rating = rating, review = review))
                }
            } else {
                ReviewCard(rating = request.rating ?: 0, review = request.review.orEmpty())
            }
        }
        if (request.status == RequestStatus.IN_PROGRESS){
            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orange)),
                onClick = { onRequestUpdated(request.copy(status = RequestStatus.COMPLETED)) }
            ) {
                Text(text = "Mark as complete")
            }
        }
    }
}

@Composable
fun BidItem(modifier: Modifier = Modifier, bid: Bid, onBidAction: (Boolean) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .background(
                color = colorResource(id = R.color.orange50),
                shape = RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(5.dp))
        SubcomposeAsyncImage(
            model = bid.garage?.images?.getOrNull(0),
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .size(70.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = bid.garage?.name ?: "Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Bid",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = bid.garage?.location ?: "Location",
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    text = bid.price,
                    fontWeight = FontWeight.Normal
                )
            }
            if (bid.bidStatus == BidStatus.PENDING) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    OutlinedButton(
                        onClick = { onBidAction(false) },
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f), // Adjust width for equal buttons
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        border = BorderStroke(color = Color.Red, width = 1.dp),
                        elevation = ButtonDefaults.buttonElevation(8.dp)
                    ) {
                        Text(
                            text = "Decline",
                            color = colorResource(id = R.color.white),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedButton(
                        onClick = { onBidAction(true) },
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f), // Adjust width for equal buttons
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.lightGreen)
                        ),
                        border = BorderStroke(color = colorResource(id = R.color.lightGreen), width = 1.dp),
                        elevation = ButtonDefaults.buttonElevation(8.dp)
                    ) {
                        Text(
                            text = "Accept",
                            color = colorResource(id = R.color.white),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ReviewInputCard(onSubmitReview: (Int, String) -> Unit = { _, _ -> }){
    var reviewText by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        RatingView{
            rating = it
        }
        TextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            placeholder = {
                androidx.compose.material.Text(
                    textAlign = TextAlign.Start,
                    text = "Write a review",
                    fontFamily = FontFamily(Font(R.font.googlesanslight)),
                    fontWeight = FontWeight.Light
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.offWhite),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = colorResource(id = R.color.orange)
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(.9f)
                .height(150.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orange)),
            onClick = {
                onSubmitReview(rating, reviewText)
            }
        ) {
            Text(text = "Submit Rating")
        }
    }
}

@Composable
fun ReviewCard(rating: Int, review: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        RatingView(initialRating = rating, isEnabled = false)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = review)
    }
}

//@Preview
@Composable
private fun RatingView(initialRating :Int =0, isEnabled: Boolean = true, onRatingChange: (Int) -> Unit = {}) {
    var rating by remember { mutableIntStateOf(initialRating) }
    val outlinedStar = painterResource(id = R.drawable.outlined_star)
    val filledStar = painterResource(id = R.drawable.filled_star)
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(5) { index ->
            Image (
                painter = if (index < rating) filledStar else outlinedStar,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        if (isEnabled) {
                            rating = index + 1
                            onRatingChange(rating)
                        }
                    }
                    .padding(4.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun RequestBidScreenPreview() {
    val request = Request(
        carModel = "honda City R",
        description = "description",
        city = "Lahore",
        bids = mapOf(
            "bidder1" to Bid("1",  price = "100.00", garage = Garage(name = "Pak Wheel", city = "Lahore"))
        )
    )
    GarageGuruFypUserTheme {
        RequestDetailsScreen(request = request, onBackPress = {}, onRequestUpdated = {})
    }
}