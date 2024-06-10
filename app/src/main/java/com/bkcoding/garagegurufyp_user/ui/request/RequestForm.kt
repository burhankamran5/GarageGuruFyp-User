package com.bkcoding.garagegurufyp_user.ui.request

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Request
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.ui.component.CircleProgressIndicator
import com.bkcoding.garagegurufyp_user.utils.CityDropDown

@Composable
fun UserRequestForm(navController: NavController, userViewModel: UserViewModel = hiltViewModel()) {
    var isLoading by rememberSaveable { mutableStateOf(false) }

    when (userViewModel.postRequestResponse) {
        Result.Loading -> {
            isLoading = true
        }

        is Result.Success -> {
            navController.popBackStack()
        }

        is Result.Failure -> {}
        else -> {}
    }
    UserRequestForm(isLoading = isLoading, onDoneClick = { carModel, city, description, imageList ->
        userViewModel.postRequest(Request(
            imageUris = imageList,
            carModel = carModel,
            description = description,
            city = city,
            customer = userViewModel.userPreferences.getCustomer()!!
        ))
    })
}

@Composable
private fun UserRequestForm(isLoading: Boolean, onDoneClick: (String, String, String, List<Uri>) -> Unit) {
    val context = LocalContext.current
    var carModel by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { selectImages = it }


    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Post Your Request",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Text(
                text = "Your Car Model",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            TextField(
                value = carModel,
                onValueChange = { carModel = it },
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .background(color = colorResource(id = R.color.white))
                    .border(
                        BorderStroke(width = 2.dp, color = colorResource(id = R.color.black)),
                        shape = RoundedCornerShape(10)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                textStyle = TextStyle(fontSize = 15.sp, color = Color.Black)

            )
            Text(
                text = "Your City",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            CityDropDown(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(0.9f)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .padding(10.dp)
            ) {
                city = it.name
            }
            Text(
                text = "State Your Problem",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(.9f)
                    .background(color = colorResource(id = R.color.white))
                    .border(
                        BorderStroke(width = 2.dp, color = colorResource(id = R.color.black)),
                        shape = RoundedCornerShape(10)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                textStyle = TextStyle(fontSize = 15.sp, color = Color.Black)

            )

            OutlinedButton(
                onClick = { galleryLauncher.launch("image/*") },
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                ),
                border = BorderStroke(color = colorResource(id = R.color.white), width = 2.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(
                    text = "Choose Images from Gallery",
                    color = colorResource(id = R.color.white),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.googlesansbold)),fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            LazyHorizontalGrid(rows = GridCells.Fixed(1), modifier = Modifier.height(50.dp)) {
                items(selectImages) { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = null,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                selectImages = selectImages
                                    .toMutableList()
                                    .apply { remove(uri) }
                            },
                    )
                }
            }

            OutlinedButton(
                onClick = {
                    if (carModel.isEmpty() || city.isEmpty() || description.isEmpty() || selectImages.isEmpty()) {
                        context.showToast(context.getString(R.string.empty_error))
                    } else {
                        onDoneClick(carModel, city, description, selectImages)
                    }
                },
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(.6f)
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                ),
                border = BorderStroke(color = colorResource(id = R.color.white), width = 2.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(
                    text = "Done",
                    color = colorResource(id = R.color.white),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.googlesansbold)),fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        if (isLoading) CircleProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}


@Preview
@Composable
fun RequestPreview() {
    UserRequestForm(isLoading = true, onDoneClick = { _, _, _, _ -> })
}