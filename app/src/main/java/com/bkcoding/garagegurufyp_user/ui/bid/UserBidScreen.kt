package com.bkcoding.garagegurufyp_user.ui.bid

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bkcoding.garagegurufyp_user.R

@Preview
@Composable
fun UserBidScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.9f)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Garage's Offers",
            modifier = Modifier,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(R.font.googlesansextrabold)),
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        BidItemDesign()
        BidItemDesign()
        BidItemDesign()
        BidItemDesign()
    }
}

@Composable
fun BidItemDesign() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.garage),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .fillMaxHeight(),
                contentScale = ContentScale.FillBounds

            )
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "PakWheel Pakistan Garage",
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "A main compartments with double zipper. Zippered front pocket",
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.googlesansregular)),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Bid:",
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.googlesansbold))
                    )
                    Text(
                        text = "4000.RS",
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.googlesansregular))
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Divider(thickness = 1.dp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    OutlinedButton(
                        onClick = {},
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
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.googlesansbold)),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {},
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
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.googlesansbold)),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}