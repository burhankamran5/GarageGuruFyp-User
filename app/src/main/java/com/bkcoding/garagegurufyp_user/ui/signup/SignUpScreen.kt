package com.bkcoding.garagegurufyp_user.ui.signup

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bkcoding.garagegurufyp_user.R

@Composable
fun SignUpScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFEEA044),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {
        Text(
            text = "Create an account to get started.",
            color = colorResource(id = R.color.black),
            fontSize = 35.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Column {
            Image(painter = painterResource(id = R.drawable.ic_signup_user),
                contentDescription ="",
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clipToBounds()
            )

            Text(
                text = "Signup as User",
                color = colorResource(id = R.color.black),
                fontSize = 25.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Row {
                Text(
                    text = stringResource(id = R.string.sign_up_user),
                    color = colorResource(id = R.color.black),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

        }
    }
}

@Preview
@Composable
fun SignUpPreview() {
    SignUpScreen()
}