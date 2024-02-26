package com.bkcoding.garagegurufyp_user.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val pageState = rememberPagerState(pageCount = { 3 })
    OnBoardingViewPager(pagerState = pageState,navController)
    PageIndicator(pagerState = pageState)

}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun OnBoardingViewPager(pagerState: PagerState,navController: NavController) {
    HorizontalPager(
        state = pagerState, modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFEEA044),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) { page ->
        when (page) {
            0 -> WelcomeScreen()
            1 -> InfoScreen()
            2 -> GetStartedScreen(navController)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(
   pagerState: PagerState,
   modifier: Modifier = Modifier,
) {
    Box(

        modifier = Modifier
           .fillMaxSize()
           .padding(bottom = 30.dp)
    ) {
        Row(
            modifier = modifier
               .fillMaxWidth()
               .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) {
                Indicator(
                    isSelected = it == pagerState.currentPage,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
private fun Indicator(
   isSelected: Boolean,
   modifier: Modifier = Modifier,
) {
    val color = if (isSelected) Color.Black else Color.Gray

    Box(
        modifier = modifier
           .size(15.dp)
           .clip(CircleShape)
           .background(color)
    )
}
