package com.bkcoding.garagegurufyp_user.utils


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion.Default
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private const val APP_BUTTON_LINE_HEIGHT = 22
private const val XSMALL_STYLE_LINE_HEIGHT = 18
private val DEFAULT_TEXT_COLOR = Color.White
private const val DEFAULT_FONT_SIZE = 24
private val platformTextStyle = PlatformTextStyle(includeFontPadding = true)

@Immutable
data class AppTypography(
    private val fontFamily: FontFamily = Default,
    private val textStyleRegular: TextStyle = TextStyle(color = DEFAULT_TEXT_COLOR, fontSize = DEFAULT_FONT_SIZE.sp, fontFamily = fontFamily, platformStyle = platformTextStyle),
    private val textStyleBold: TextStyle = textStyleRegular + TextStyle(fontWeight = FontWeight.Bold, platformStyle = platformTextStyle),
    private val textStyleBoldItalic: TextStyle = textStyleBold + TextStyle(fontStyle = FontStyle.Italic, platformStyle = platformTextStyle)
) {

    val title = Title()
    val body = Body()
    val label = Label()
    val headLine = Headline()
    val button = Button()
    val spanStyle = SpanStyle()

    @javax.annotation.concurrent.Immutable
    inner class Body {
        val large = textStyleRegular + TextStyle(fontSize = 22.sp)
        val largeBold = textStyleBold + TextStyle(fontSize = 22.sp)
        val largeItalicBold = textStyleBoldItalic + TextStyle(fontSize = 22.sp)
        val medium = textStyleRegular + TextStyle(fontSize = 16.sp)
        val small = textStyleRegular + TextStyle(fontSize = 14.sp, lineHeight = 18.sp)
        val smallBold = textStyleBold + TextStyle(fontSize = 14.sp)
        val xSmall = textStyleRegular + TextStyle(fontSize = 12.sp)
        val xSmallBold = textStyleBold + TextStyle(fontSize = 12.sp)
        val xSmallItalicBold = textStyleBoldItalic + TextStyle(fontSize = 12.sp)
        val mini = textStyleRegular + TextStyle(fontSize = 9.sp)
    }

    @Immutable
    inner class Button {
        val primary = textStyleBoldItalic + TextStyle(fontSize = 14.sp, lineHeight = APP_BUTTON_LINE_HEIGHT.sp)
        val secondary = textStyleBoldItalic + TextStyle(fontSize = 12.sp, lineHeight = APP_BUTTON_LINE_HEIGHT.sp)
        val crusherItalic = textStyleBoldItalic + TextStyle(fontSize = 18.sp, lineHeight = APP_BUTTON_LINE_HEIGHT.sp)
    }

    @javax.annotation.concurrent.Immutable
    inner class Headline {
        val display = textStyleBoldItalic + TextStyle(fontSize = 64.sp)
        val xLarge = textStyleBold + TextStyle(fontSize = 42.sp)
        val xLargeBoldItalic = textStyleBoldItalic + TextStyle(fontSize = 42.sp)
        val xxLargeBoldItalic = textStyleBoldItalic + TextStyle(fontSize = 48.sp)
        val large = textStyleBold + TextStyle(fontSize = 32.sp)
        val medium = textStyleBold + TextStyle(fontSize = 24.sp)
        val mediumBoldItalic = textStyleBoldItalic + TextStyle(fontSize = 24.sp)
        val small = textStyleBold + TextStyle(fontSize = 18.sp)
        val smallBoldItalic = textStyleBoldItalic + TextStyle(fontSize = 18.sp)
        val xSmall = textStyleBold + TextStyle(fontSize = 14.sp, lineHeight = XSMALL_STYLE_LINE_HEIGHT.sp)
        val xSmallBoldItalic = textStyleBoldItalic + TextStyle(fontFamily = fontFamily, fontSize = 14.sp, lineHeight = XSMALL_STYLE_LINE_HEIGHT.sp)
    }

    @javax.annotation.concurrent.Immutable
    inner class Label {
        val large = textStyleBold + TextStyle(fontSize = 28.sp)
        val largeItalicBold = textStyleBoldItalic + TextStyle(fontSize = 28.sp)
        val medium = textStyleBold + TextStyle(fontSize = 20.sp)
        val mediumBoldItalic = textStyleBoldItalic + TextStyle(fontSize = 20.sp)
        val small = textStyleBold + TextStyle(fontSize = 16.sp)
        val smallItalicBold = textStyleBoldItalic + TextStyle(fontSize = 16.sp)
        val xSmall = textStyleBold + TextStyle(fontSize = 12.sp)
        val xSmallItalicBold = textStyleBoldItalic + TextStyle(fontSize = 12.sp)
        val xxSmall = textStyleBold + TextStyle(fontSize = 10.sp)
        val mini = textStyleBold + TextStyle(fontSize = 9.sp)
        val miniItalicBold = textStyleBoldItalic + TextStyle(fontSize = 9.sp)
    }

    @javax.annotation.concurrent.Immutable
    inner class Title {
        val regular = textStyleBoldItalic + TextStyle(fontSize = 12.sp, fontFamily = fontFamily)
        val small = textStyleBold + TextStyle(fontSize = 10.sp, fontFamily = fontFamily)
        val smallItalicBold = textStyleBoldItalic + TextStyle(fontSize = 10.sp, fontFamily = fontFamily)
    }

    @javax.annotation.concurrent.Immutable
    inner class SpanStyle {
        private val spanStyleBase = SpanStyle(color = Color.White, fontSize = 24.sp, fontFamily = fontFamily)
        val spanStyleBaseBold = spanStyleBase.merge(SpanStyle(fontWeight = FontWeight.Bold))
    }
}
