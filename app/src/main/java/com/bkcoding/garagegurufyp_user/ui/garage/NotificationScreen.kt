package com.bkcoding.garagegurufyp_user.ui.garage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.NotificationData
import com.bkcoding.garagegurufyp_user.extensions.isVisible
import com.bkcoding.garagegurufyp_user.extensions.progressBar
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.bkcoding.garagegurufyp_user.ui.theme.Typography
import com.bkcoding.garagegurufyp_user.utils.convertMillisToDate
import com.bkcoding.garagegurufyp_user.utils.getInboxRelativeTime
import io.github.rupinderjeet.kprogresshud.KProgressHUD

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(
    navController: NavController,
    garageViewModel: GarageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val error = garageViewModel.error
    val isLoading = garageViewModel.isLoading
    val progressBar: KProgressHUD = remember { context.progressBar() }
    progressBar.isVisible(isLoading)
    if (error.isNotEmpty()) context.showToast(error)

    LaunchedEffect(key1 = Unit) {
        garageViewModel.getPushNotificationFromDB()
    }

    NotificationScreen(
        notificationDataList = garageViewModel.getNotificationListResponse,
        onBackPress = { navController.popBackStack() })
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun NotificationScreen(
    notificationDataList: List<NotificationData>?,
    onBackPress: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 50.dp, end = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.clickable { onBackPress() }
            )
            Text(
                text = stringResource(id = R.string.notification),
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.heightIn(max = 800.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(notificationDataList.orEmpty()) {
                NotificationCard(modifier = Modifier, it)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun NotificationCard(modifier: Modifier = Modifier, notificationData: NotificationData) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = notificationData.thumbnail,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.fillMaxWidth()){
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.black),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.googlesansbold))
                        )
                    ) {
                        append("${notificationData.title}!\n")
                    }
                    append(notificationData.description)
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                style = Typography.bodySmall,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 10.dp, end = 20.dp)
            )
            Text(
                text = getInboxRelativeTime(convertMillisToDate(notificationData.sentAt as? Long ?: 0)),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                style = Typography.bodySmall,
                color = colorResource(id = R.color.orange),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 5.dp,end = 10.dp)
                    .align(Alignment.End)
            )
        }
    }
    Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 5.dp))

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun NotificationScreenPreview(){
    GarageGuruFypUserTheme {
        NotificationScreen(notificationDataList = listOf(NotificationData(title = "Bid Accepted", description = "Ali Accept you Bid!")), onBackPress = {})
    }
}