package com.bkcoding.garagegurufyp_user.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.bkcoding.garagegurufyp_user.ui.theme.Typography

@Composable
fun GarageButton(modifier: Modifier = Modifier, buttonText: String, onButtonClick: () -> Unit) {
    Button(
        modifier = modifier.height(48.dp),
        onClick = { onButtonClick() },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.orange)
        ),
        shape = RoundedCornerShape(246.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buttonText,
                fontSize = 17.62.sp,
                fontWeight = FontWeight.Bold,
                style = Typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GarageButtonPreview() {
    GarageGuruFypUserTheme {
        GarageButton(modifier = Modifier, buttonText = "Sign in") {}
    }
}