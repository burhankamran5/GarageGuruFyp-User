package com.bkcoding.garagegurufyp_user.ui.component

import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircleProgressIndicator(modifier: Modifier = Modifier){
    CircularProgressIndicator(
        modifier = modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}