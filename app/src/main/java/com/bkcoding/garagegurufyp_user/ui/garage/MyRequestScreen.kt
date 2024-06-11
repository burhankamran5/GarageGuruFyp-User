package com.bkcoding.garagegurufyp_user.ui.garage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.ui.component.CircleProgressIndicator
import com.bkcoding.garagegurufyp_user.ui.customer.RequestItem
import com.bkcoding.garagegurufyp_user.ui.theme.Typography

@Composable
fun MyRequestScreen(
    navController: NavController,
    garageViewModel: GarageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val error = garageViewModel.error
    val isLoading = garageViewModel.isLoading
    if (error.isNotEmpty()) context.showToast(error)
    LaunchedEffect(key1 = Unit) {
        garageViewModel.getRequest()
    }
    MyRequestScreen(
        isLoading = isLoading,
        requestList = garageViewModel.getRequestResponse?.filter {
            it.bids.containsKey(
                garageViewModel.userPreferences.getGarage()?.id
            )
        },
        onBackPress = { navController.popBackStack() })
}

@Composable
private fun MyRequestScreen(
    isLoading: Boolean,
    requestList: List<Request>?,
    onBackPress: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.clickable { onBackPress() }
                )
                Text(
                    text = stringResource(id = R.string.my_request),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    style = Typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier.padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(requestList.orEmpty()) {
                    RequestItem(request = it, onRequestClick = { })
                }
            }
        }
        if (isLoading) CircleProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}
