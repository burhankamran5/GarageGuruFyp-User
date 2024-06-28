package com.bkcoding.garagegurufyp_user.ui.garage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.dto.RequestStatus
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.ui.component.LinearProgress
import com.bkcoding.garagegurufyp_user.ui.component.TabLayoutButton
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
    val context = LocalContext.current
    var isOnGoingSelect by rememberSaveable { mutableStateOf(context.getString(R.string.requested)) }

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
            TabLayoutButton(
                modifier = Modifier.padding(horizontal = 15.dp),
                buttonText = listOf(
                    stringResource(id = R.string.requested),
                    stringResource(id = R.string.active),
                    stringResource(id = R.string.completed)
                ),
                isFirstSelect = isOnGoingSelect,
                onButtonClick = {
                    isOnGoingSelect = it
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            if(isLoading) LinearProgress(modifier = Modifier.padding(start = 15.dp, top = 10.dp, end = 15.dp))
            when(isOnGoingSelect){
                stringResource(id = R.string.requested) -> RequestedItem(modifier = Modifier, requestList = requestList?.filter { it.status == RequestStatus.IN_PROGRESS })
                stringResource(id = R.string.active) -> RequestedItem(modifier = Modifier, requestList = requestList?.filter { it.status == RequestStatus.OPEN })
                stringResource(id = R.string.completed) -> RequestedItem(modifier = Modifier, requestList = requestList?.filter { it.status == RequestStatus.COMPLETED })
            }
        }
    }
}

@Composable
private fun RequestedItem(modifier: Modifier = Modifier, requestList: List<Request>?){
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
            .heightIn(max = 700.dp),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(bottom = 20.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        content = {
            items(requestList.orEmpty()) { request ->
                Column(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.cultured),
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    ) {
                        AsyncImage(
                            model = request.images.getOrNull(0),
                            contentDescription = "",
                            placeholder = painterResource(id = R.drawable.ic_placeholder),
                            error = painterResource(id = R.drawable.ic_placeholder),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(200.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                    Text(
                        text = request.carModel,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.black),
                        style = Typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        lineHeight = 10.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp, top = 5.dp, bottom = 10.dp)
                    )
                }
            }
        }
    )
}