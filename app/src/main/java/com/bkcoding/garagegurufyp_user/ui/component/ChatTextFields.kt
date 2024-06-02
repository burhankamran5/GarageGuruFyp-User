package com.bkcoding.garagegurufyp_user.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bkcoding.garagegurufyp_user.R

@Composable
fun ChatTextFields(
    modifier: Modifier = Modifier,
    textValue: String,
    onTextChange: (String) -> Unit,
    onSendPress: () -> Unit
) {
    TextField(
        value = textValue,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.write_your_message),
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
        maxLines = 5,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        trailingIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "",
                tint = colorResource(id = R.color.black),
                modifier = Modifier
                    .rotate(320f)
                    .clickable { onSendPress() }
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(30.dp)
            )
    )
}