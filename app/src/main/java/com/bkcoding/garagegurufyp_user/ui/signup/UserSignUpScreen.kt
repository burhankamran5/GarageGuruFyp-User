package com.bkcoding.garagegurufyp_user.ui.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R

@Composable
fun UserSignUpScreen(navController: NavController) {
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
            )) {

        Text(
            text = "Create your account",
            color = colorResource(id = R.color.black),
            fontSize = 23.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .fillMaxWidth()
        )

        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Enter Your Name",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White ,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Filled.Create, "", ) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )
        

        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Enter Your Email",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White ,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Filled.Email, "", ) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Enter Password",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White ,
                unfocusedIndicatorColor = Color.White
            ),
            leadingIcon = { Icon(Icons.Filled.Lock, "", ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Confirm Password",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White ,
                unfocusedIndicatorColor = Color.White
            ),
            leadingIcon = { Icon(Icons.Filled.Lock, "", ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Enter Phone-Number",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White ,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = {Icon(Icons.Filled.Phone, "", )},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Enter Your City",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White ,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = {Icon(Icons.Filled.Home, "", )},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {navController.navigate("VerifyOtpScreen"){launchSingleTop = true} },
            modifier = Modifier
                .height(70.dp)
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


@Preview(device = "id:Nexus 4")
@Preview(device = "id:pixel_6_pro")
@Composable
fun PreviewSignUp(){
//    UserSignUpScreen()
}