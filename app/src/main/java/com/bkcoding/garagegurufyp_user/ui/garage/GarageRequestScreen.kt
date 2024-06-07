package com.bkcoding.garagegurufyp_user.ui.garage

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.bkcoding.garagegurufyp_user.utils.City

@Composable
fun GarageRequestScreen(
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

    GarageRequestScreen(
        isLoading = isLoading,
        requestList = garageViewModel.getRequestResponse,
        onBackPress = { navController.popBackStack() }
    )
}

@Composable
private fun GarageRequestScreen(
    isLoading: Boolean,
    requestList: List<Request>?,
    onBackPress: () -> Unit
) {
    var filteredList by remember { mutableStateOf(listOf<Request>()) }
    LaunchedEffect(requestList) {
        if (requestList != null) {
            filteredList = requestList
        }
    }

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
                text = stringResource(id = R.string.all_request),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.neo_sans_bold)),
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.all_request),
                fontFamily = FontFamily(Font(R.font.neo_sans_bold)),
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            FilterCityDropDown {
                filteredList = requestList.orEmpty().filter { request ->
                    request.city.contains(it.name, ignoreCase = true)
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredList) {
                if (it.status == Request.RequestStatus.OPEN) Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Gray, shape = RoundedCornerShape(10.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    AsyncImage(
                        model = it.images.getOrNull(0),
                        contentDescription = "",
                        placeholder = painterResource(id = R.drawable.ic_placeholder),
                        error = painterResource(id = R.drawable.ic_placeholder),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(
                            text = it.carModel,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.neo_sans_bold)),
                            color = Color.White
                        )
                        Text(
                            text = it.description,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.neo_sans_regular)),
                            color = colorResource(id = R.color.white_semi_50)
                        )
                    }
                }
            }
        }
    }
    if (isLoading) CircularProgressIndicator()
}

@Composable
private fun FilterCityDropDown(modifier: Modifier = Modifier, onUserCitySelected: (City) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val listOfCity = listOf(City.Lahore, City.Islamabad, City.Karachi, City.Multan)

    Box(modifier = modifier.clickable { expanded = true }) {
        Row(
            modifier = Modifier
                .background(
                    color = Color.Blue,
                    shape = RoundedCornerShape(12.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter_list_alt_24),
                contentDescription = "",
                tint = Color.White
            )
            Text(
                text = stringResource(id = R.string.filter),
                fontFamily = FontFamily(Font(R.font.neo_sans_bold)),
                textAlign = TextAlign.Start,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOfCity.forEach {
                DropdownMenuItem(onClick = {
                    expanded = false
                    onUserCitySelected(it)
                },
                    text = {
                        Text(text = it.name)
                    })
            }
        }
    }
}

@Preview
@Composable
fun GarageRequestScreenPreview() {
    GarageGuruFypUserTheme {
        GarageRequestScreen(
            isLoading = true,
            requestList = null,
            onBackPress = {}
        )
    }
}