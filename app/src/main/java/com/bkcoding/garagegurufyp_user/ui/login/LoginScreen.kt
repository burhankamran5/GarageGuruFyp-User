package com.bkcoding.garagegurufyp_user.ui.login


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
fun LoginScreen(navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
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
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.mechanic_icon_com),
            contentDescription = "",
            modifier = Modifier.size(130.dp)
        )

        Text(
            text = "Car cries, Guru replies",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )


        Text(
            text = "Log-in",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 45.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        Text(
            text = "Email",
            fontSize = 18.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )


        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Enter Your Email",
                    fontFamily = FontFamily.Cursive,
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
            leadingIcon = {Icon(Icons.Filled.Email, "", )},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        Text(
            text = "Password",
            fontSize = 18.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        TextField(
            value = "",
            onValueChange = {},
            singleLine = true,
            placeholder = { Text(text = "Enter Your Password",
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White ,
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(.9f)
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(.6f)
                .padding(10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.white)
            ),
            border = BorderStroke(color = colorResource(id = R.color.orange), width = 2.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text(
                text = "LOGIN",
                color = colorResource(id = R.color.black),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = "Don't have a account?",
            fontSize = 22.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
        )

        Text(
            text = "Sign-Up",
            fontSize = 22.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.orange50),
            modifier = Modifier
                .clickable(
                   onClick = {navController.navigate("SignUpScreen")},
                    indication = null,
                    interactionSource = interactionSource)
        )
    }

}


@Preview(device = "spec:parent=Nexus 4")
@Composable
fun LoginScreenPreview() {
//    LoginScreen()
}
