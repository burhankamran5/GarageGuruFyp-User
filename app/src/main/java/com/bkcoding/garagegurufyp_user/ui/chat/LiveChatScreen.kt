package com.bkcoding.garagegurufyp_user.ui.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bkcoding.garagegurufyp_user.R

@Preview
@Composable
fun ChatScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp)) {
        MessageInputField(modifier = Modifier.align(Alignment.BottomCenter))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ChatToolbar()
        }
    }
 }

@Composable
fun MessageInputField(modifier: Modifier = Modifier) {
    Row (modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,){
        TextField(
            value = "Hello!",
            onValueChange = {},
            modifier = modifier
                .fillMaxWidth(.8f)
                .background(color = colorResource(id = R.color.white))
                .border(
                    BorderStroke(width = 1.dp, color = colorResource(id = R.color.black)),
                    shape = RoundedCornerShape(15.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            textStyle = TextStyle(fontSize = 15.sp, color = Color.Black)

        )
        Image(
            painter = painterResource(id = R.drawable.send_icon),
            contentDescription = "",
            modifier = Modifier
                .clickable {}
                .size(30.dp)
        )
    }
}

@Composable
fun ChatToolbar() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 15.dp)) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp)){
            Image(painter = painterResource(id = R.drawable.back_arrow), contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.TopStart))

            Text(
                text = "PakWheel Garage",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )

        }
    }
}

@Composable
fun MessagesItem() {
    Card {

    }
}
