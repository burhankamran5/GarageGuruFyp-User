package com.bkcoding.garagegurufyp_user.ui.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.ui.login.UserStorageVM

@Composable
fun GetStartedScreen(navController: NavController) {
    val userStorageVM: UserStorageVM = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.connect_clarify),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(R.drawable.chat), contentDescription = "", modifier = Modifier
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.chat_directly),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(10.dp)
        )

        OutlinedButton(
            onClick = {
                userStorageVM.setIsFirstLaunch()
                navController.popBackStack("OnBoardingScreen",true)
                navController.navigate("LoginScreen")
            },
            modifier = Modifier
                .height(55.dp)
                .width(180.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.orange)
            ),
            border = BorderStroke(color = colorResource(id = R.color.white), width = 2.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text(
                text = "Get Started",
                color = colorResource(id = R.color.white),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

}