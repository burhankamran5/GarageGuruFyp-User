package com.bkcoding.garagegurufyp_user.ui.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R


@Composable
fun ChooseSignUp(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
            fontSize = 23.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .fillMaxWidth()
        )

        Column (modifier = Modifier.fillMaxWidth()){
            Image(painter = painterResource(id = R.drawable.ic_signup_user),
                contentDescription ="",
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = "Signup as User",
                color = colorResource(id = R.color.black),
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Row(horizontalArrangement = Arrangement.Absolute.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.sign_up_user),
                    color = colorResource(id = R.color.black),
                    fontSize = 13.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .padding(top = 5.dp, start = 10.dp)
                )
                OutlinedButton(
                    onClick = {navController.navigate("UserSignUpScreen"){ launchSingleTop = true}},
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(.6f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.orange)
                    ),
                    border = BorderStroke(color = colorResource(id = R.color.white), width = 2.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp)
                ) {
                    Text(
                        text = "Continue",
                        color = colorResource(id = R.color.white),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
Spacer(modifier = Modifier.height(15.dp))
        Column {
            Image(painter = painterResource(id = R.drawable.ic_signup_garage),
                contentDescription ="",
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = "Signup as Garage",
                color = colorResource(id = R.color.black),
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Row(horizontalArrangement =  Arrangement.Absolute.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.grow_your_business),
                    color = colorResource(id = R.color.black),
                    fontSize = 13.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .padding(top = 5.dp, start = 10.dp)

                )
                OutlinedButton(
                    onClick = {navController.navigate("GarageSignUpScreen"){ launchSingleTop = true}},
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(.6f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.orange)
                    ),
                    border = BorderStroke(color = colorResource(id = R.color.white), width = 2.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp)
                ) {
                    Text(
                        text = "Continue",
                        color = colorResource(id = R.color.white),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }



    }
}

//@Preview(device = "id:Nexus 4")
//@Preview(device = "id:pixel_6_pro")
//@Composable
//fun ChooseSignUp() {
//    ChooseSignUp()
//}