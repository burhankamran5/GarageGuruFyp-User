import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UserHomeScreen(navController:NavController) {
    UserHomeScreen()
}

@Composable
private fun UserHomeScreen(){
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = colorResource(id = R.color.offWhite)),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Welcome,Burhan Kamran",
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.height(30.dp))
        AutoSwipeViewPager(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Car Cries,Guru Replies",
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.black), modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        ItemList()

    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun AutoSwipeViewPager(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = {3})
    val coroutineScope = rememberCoroutineScope()

    val imageList = listOf(R.drawable.caricon2, R.drawable.chat, R.drawable.mechanic_icon_com)

    Box(
        modifier = modifier
            .fillMaxWidth(.6f)
            .height(220.dp)
            .padding(horizontal = 8.dp)
            .background(
                color = colorResource(id = R.color.orange50),
                shape = RoundedCornerShape(10.dp)
            )
//            .clip(RoundedCornerShape(5.dp))
//            .border(border = BorderStroke(1.dp, color = colorResource(id = R.color.orange50)))
//            .shadow(elevation = 4.dp, clip = true)

    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = imageList[page]),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(8.dp) // Add padding for images
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        LaunchedEffect(pagerState.currentPage) {
            while (true) {
                delay(3000) // Adjust swipe interval as needed
                coroutineScope.launch {
                    pagerState.animateScrollToPage((pagerState.currentPage + 1) % imageList.size)
                }
            }
        }
    }
}

@Composable
fun ItemList() {
    LazyVerticalGrid(modifier = Modifier.heightIn(max = 800.dp), columns = GridCells.Fixed(2), contentPadding = PaddingValues(12.dp)) {
        items(titleList){
            ItemDesign(title = it)
        }
    }
}

val titleList = listOf("Request","View Garages", "Bids","Chat")

@Composable
fun ItemDesign(modifier: Modifier = Modifier,title: String) {
    Box(
        modifier = modifier
            .size(130.dp)
            .clickable { }
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Box(modifier = Modifier.matchParentSize()) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun UserHomeScreenPreview(){
    GarageGuruFypUserTheme {
        UserHomeScreen()
    }
}

