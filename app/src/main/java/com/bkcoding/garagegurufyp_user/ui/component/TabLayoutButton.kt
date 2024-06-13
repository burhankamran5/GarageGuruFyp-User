package com.bkcoding.garagegurufyp_user.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.extensions.clickableWithOutRipple
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.bkcoding.garagegurufyp_user.ui.theme.Typography

@Composable
fun TabLayoutButton(
    modifier: Modifier = Modifier,
    buttonText: List<String>,
    isFirstSelect: String,
    onButtonClick: (String) -> Unit
) {
    var isFirstButtonSelect by rememberSaveable {
        mutableStateOf(isFirstSelect)
    }

    Row(
        modifier = modifier
            .height(52.dp)
            .background(
                color = colorResource(id = R.color.offWhite),
                shape = RoundedCornerShape(250.dp)
            )
    ) {
        buttonText.forEach {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .background(
                        color = if (isFirstButtonSelect == it) colorResource(id = R.color.orange) else Color.Transparent,
                        shape = RoundedCornerShape(250.dp)
                    )
                    .clickableWithOutRipple {
                        isFirstButtonSelect = it
                        onButtonClick(it)

                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    style = Typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun TabLayoutButtonPreview() {
    GarageGuruFypUserTheme {
        TabLayoutButton(
            modifier = Modifier,
            buttonText = listOf("About", "Post"),
            isFirstSelect = "About",
            onButtonClick = {})
    }
}