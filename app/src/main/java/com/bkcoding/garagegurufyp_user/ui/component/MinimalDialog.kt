package com.bkcoding.garagegurufyp_user.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.ui.theme.Typography

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit, onBidClick: (Int) -> Unit) {
    var textValue by rememberSaveable {
        mutableStateOf("")
    }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.bid_amount),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                style = Typography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = textValue,
                onValueChange = {
                    textValue = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enter_your_bid_amount),
                        color = Color.Gray,
                    )
                },
                textStyle = TextStyle(colorResource(id = R.color.black)),
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(30.dp)
                    )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = { onBidClick(textValue.toInt()) }) {
                Text(
                    text = stringResource(id = R.string.bid),
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}