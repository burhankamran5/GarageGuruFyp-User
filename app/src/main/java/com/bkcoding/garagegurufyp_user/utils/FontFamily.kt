package com.bkcoding.garagegurufyp_user.utils


import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.bkcoding.garagegurufyp_user.R


interface FontFamilyProvider {
    fun fontFamily(): FontFamily
}

class NeoSansFontFamily : FontFamilyProvider {
    override fun fontFamily() = FontFamily(
        Font(resId = R.font.neo_sans_regular),
        Font(resId = R.font.neo_sans_italic, style = FontStyle.Italic),
        Font(resId = R.font.neo_sans_bold, weight = FontWeight.Bold),
        Font(resId = R.font.neo_sans_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    )
}