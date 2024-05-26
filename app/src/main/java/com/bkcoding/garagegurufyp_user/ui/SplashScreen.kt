package com.bkcoding.garagegurufyp_user.ui

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R

@Composable
fun SplashScreen(navController: NavController? = null) {
    Box (modifier = Modifier.fillMaxSize()) {
        AppImage(modifier = Modifier.align(Alignment.Center).clip(CircleShape).size(192.dp),  resource = R.drawable.splash_inset)
    }
}

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    @DrawableRes resource: Int,
) {
    AndroidView(
        modifier = modifier.clip(CircleShape),
        factory = { context ->
            ImageView(context).apply {
                setImageResource(resource)
            }
        },
        update = { it.setImageResource(resource) }
    )
}