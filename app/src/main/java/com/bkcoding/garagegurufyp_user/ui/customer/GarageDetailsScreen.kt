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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.extensions.clickableWithOutRipple
import com.bkcoding.garagegurufyp_user.extensions.openDialPanel
import com.bkcoding.garagegurufyp_user.extensions.openEmail
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.ui.component.GarageButton
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.bkcoding.garagegurufyp_user.ui.theme.Typography
import com.google.firebase.database.ServerValue
import com.google.gson.Gson

@Composable
fun GarageDetailsScreen(navController: NavController, garage: Garage?) {
    GarageDetailsScreen(
        garage = garage,
        onBackPress = { navController.popBackStack() },
        onChatClick = {
            navController.navigate(Screen.ChatScreen.route + "/${Uri.encode(Gson().toJson(it))}")
        }
    )
}

@Composable
private fun GarageDetailsScreen(garage: Garage?, onBackPress: () -> Unit, onChatClick: (Conversation) -> Unit) {
    val context = LocalContext.current
    var selectImageIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(30.dp)
                .background(color = colorResource(id = R.color.orange), shape = CircleShape)
                .clickableWithOutRipple { onBackPress() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = stringResource(id = R.string.garage_detail),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.poppinssemibold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            model = garage?.images?.getOrNull(selectImageIndex),
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .background(
                    color = colorResource(id = R.color.bright_gray),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            LazyRow(
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                itemsIndexed(garage?.images.orEmpty()) { index, it ->
                    AsyncImage(
                        model = it,
                        contentDescription = "",
                        placeholder = painterResource(id = R.drawable.ic_placeholder),
                        error = painterResource(id = R.drawable.ic_placeholder),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(start = 1.dp, end = 1.dp)
                            .border(
                                width = 1.dp,
                                color = colorResource(id = R.color.white),
                                shape = RoundedCornerShape(5.dp)
                            )
                            .clip(shape = RoundedCornerShape(5.dp))
                            .clickable { selectImageIndex = index }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f)

            ) {
                Text(
                    text = garage?.approvalStatus ?: "Status",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = Typography.bodyLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.orange50),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = colorResource(id = R.color.orange50)
            )
            Text(
                text = garage?.rating ?: "0.0",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                style = Typography.bodySmall,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 2.dp)
            )
            Text(
                text = "(0 reviews)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                style = Typography.bodySmall,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 2.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = garage?.name ?: "Name",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = garage?.location ?: "location",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            style = Typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 5.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.about),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = garage?.description ?: "description",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            style = Typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = stringResource(id = R.string.contact_detail),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            style = Typography.bodyLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = "",
            )
            Text(
                text = stringResource(id = R.string.phone),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.black),
                style = Typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = garage?.phoneNumber ?: "Phone",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                style = Typography.bodyLarge,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "",
                tint = colorResource(id = R.color.black),
            )
            Text(
                text = stringResource(id = R.string.email),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.black),
                style = Typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = garage?.email.orEmpty(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.black),
                style = Typography.bodyLarge,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        context.openEmail(garage?.email.orEmpty())
                    }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GarageButton(
                modifier = Modifier
                    .weight(.8f)
                    .padding(bottom = 20.dp),
                buttonText = stringResource(id = R.string.chat_now)
            ) {
                onChatClick(
                    Conversation(
                        seen = false,
                        userId = garage?.id.orEmpty(),
                        userName = garage?.name.orEmpty(),
                        profileImage = garage?.images?.getOrNull(0).orEmpty(),
                        createdAt = ServerValue.TIMESTAMP
                    )
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(48.dp)
                    .background(
                        color = colorResource(id = R.color.orange),
                        shape = CircleShape
                    )
                    .clickable {
                        context.openDialPanel(garage?.phoneNumber.orEmpty())
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
fun GarageDetailsScreenPreview() {
    GarageGuruFypUserTheme {
        GarageDetailsScreen(garage = Garage(
            name = "Pak Wheels",
            location = "Ali Town, Lahore",
            description = "Garage Garage Garage Garage Garage Garage",
            rating = "4.3",
            email = "abc@gmail.com",
            phoneNumber = "+923045593294",
            images = listOf("", "", "", "")
        ),
            onBackPress = {},
            onChatClick = {}
        )
    }
}