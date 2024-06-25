package com.bkcoding.garagegurufyp_user.ui.customer

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.extensions.openEmail
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.google.android.datatransport.BuildConfig

@Composable
fun MoreScreen(navController: NavController, userViewModel: UserViewModel = hiltViewModel()) {
    MoreScreen(
        onLogoutClick = {
            userViewModel.userPreferences.signOut()
            navController.navigate(Screen.LoginScreen.route) {
                popUpTo(navController.graph.id)
            }
        },
        onAboutUsClick = {
            navController.navigate(Screen.AboutUsScreen.route)
        }
    )
}

@Composable
fun MoreScreen(onLogoutClick: () -> Unit, onAboutUsClick: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.account),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.googlesansbold)),
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.orange),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { context.openEmail(context.getString(R.string.contact_email)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.contact_us),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 15.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.padding(end = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.orange),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { onAboutUsClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.about_us_title),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 15.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.padding(end = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.orange),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    shareApp(context)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.share_app),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 15.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.padding(end = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.orange),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.app_version),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 15.dp)
            )
            Text(
                text = BuildConfig.VERSION_NAME,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 15.dp, horizontal = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.orange),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { onLogoutClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.log_out),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 15.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    }
}

fun shareApp(context: Context){
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain" // MIME type for sharing text
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Your Awesome App!")
    shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey! Check out this cool app: ${context.getString(R.string.app_name)}") // Replace with your app info
    val chooserIntent = Intent.createChooser(shareIntent, "Share App Using")
    context.startActivity(chooserIntent)
}

@Preview
@Composable
fun MoreScreenPreview(){
    GarageGuruFypUserTheme {
        MoreScreen(onLogoutClick = {}, onAboutUsClick = {})
    }
}