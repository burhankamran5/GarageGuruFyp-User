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
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bkcoding.garagegurufyp_user.R

@Composable
fun UserRequestForm() {
    var carModel by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }
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
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            color = colorResource(id = R.color.orange),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )


        Text(
            text = "Your Car Model",
            fontSize = 15.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.orange50),
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
                    BorderStroke(width = 3.dp, color = colorResource(id = R.color.orange50)),
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
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.orange50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        TextField(
            value = city,
            onValueChange = { city = it },
            modifier = Modifier
                .fillMaxWidth(.9f)
                .background(color = colorResource(id = R.color.white))
                .border(
                    BorderStroke(width = 3.dp, color = colorResource(id = R.color.orange50)),
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
            text = "State Your Problem",
            fontSize = 15.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.orange50),
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
                    BorderStroke(width = 3.dp, color = colorResource(id = R.color.orange50)),
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
                fontWeight = FontWeight.Bold,
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
            onClick = {},
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
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


    }

}


@Preview
@Composable
fun RequestPreview() {
    UserRequestForm()
}