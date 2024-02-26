package com.bkcoding.garagegurufyp_user.utils

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.bkcoding.garagegurufyp_user.R

object Assets {

    object TextFieldColors {

        val primaryTextFieldColors
            @Composable
            get() = TextFieldDefaults.textFieldColors(
                textColor = colorResource(id = R.color.white),
                placeholderColor = colorResource(id = R.color.orange),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
    }


}