package com.bkcoding.garagegurufyp_user.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bkcoding.garagegurufyp_user.R

@Preview
@Composable
fun ConversationsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ){
        Text(
            text = "Chat", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            textAlign = TextAlign.Start
        )
        MessageItem()
        MessageItem()
        MessageItem()
        MessageItem()
        MessageItem()
        MessageItem()
    }
}


@Composable
fun MessageItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.offWhite)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.girl_profile), contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(90.dp)
                    .padding(horizontal = 12.dp)
            )
            Column(  modifier = Modifier.padding(horizontal = 15.dp)) {
                Text(text = "PakWheels Garage", fontSize = 17.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold)
                Text(
                    text = "I knew it, are you coming? How are you coming? We are available 24/7",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}