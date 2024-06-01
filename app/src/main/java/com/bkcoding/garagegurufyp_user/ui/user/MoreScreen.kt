package com.bkcoding.garagegurufyp_user.ui.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme

@Composable
fun MoreScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "More Screen",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
        )
    }
}

@Preview
@Composable
fun MoreScreenPreview(){
    GarageGuruFypUserTheme {
        MoreScreen()
    }
}