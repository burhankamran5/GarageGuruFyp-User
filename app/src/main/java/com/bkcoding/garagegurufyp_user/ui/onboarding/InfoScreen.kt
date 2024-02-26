package com.bkcoding.garagegurufyp_user.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bkcoding.garagegurufyp_user.R

@Composable
fun InfoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.competitive_offers),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp ,
            modifier = Modifier.padding(horizontal = 8.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(R.drawable.caricon2),
            contentDescription = "",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.relax_garages_bid),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(10.dp)
        )
    }

}