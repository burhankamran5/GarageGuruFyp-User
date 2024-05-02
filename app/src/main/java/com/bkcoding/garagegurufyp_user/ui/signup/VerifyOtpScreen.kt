package com.bkcoding.garagegurufyp_user.ui.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Garage
import com.bkcoding.garagegurufyp_user.dto.User
import com.bkcoding.garagegurufyp_user.extensions.getActivity
import com.bkcoding.garagegurufyp_user.extensions.isVisible
import com.bkcoding.garagegurufyp_user.extensions.progressBar
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.ui.AuthViewModel
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import io.github.rupinderjeet.kprogresshud.KProgressHUD
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun VerifyOtpScreen(
    navController: NavController?,
    user: User,
    garage: Garage,
    authViewModel: AuthViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val progressBar: KProgressHUD = remember { context.progressBar() }
    var otp by rememberSaveable { mutableStateOf("") }

    suspend fun saveUserToDb(user: User){
        userViewModel.storeUserToDb(user).collect{ result ->
            progressBar.isVisible(result is Result.Loading)
            when (result) {
                is Result.Failure -> context.showToast(result.exception.message.toString())
                is Result.Success -> {
                    navController?.navigate(Screen.SignUpConfirmationScreen.route+"/${false}"){
                        popUpTo(navController.graph.id)
                    }
                }
                else -> {}
            }
        }
    }

    suspend fun saveGarageToDb(garage: Garage){
        userViewModel.storeGarageToDb(garage).collect{ result ->
            progressBar.isVisible(result is Result.Loading)
            when (result) {
                is Result.Failure -> context.showToast(result.exception.message.toString())
                is Result.Success -> {
                    navController?.navigate(Screen.SignUpConfirmationScreen.route+"/${true}")
                }
                else -> {}
            }
        }
    }

    suspend fun uploadGarageImages(garage: Garage) {
        userViewModel.uploadGarageImages(garage).collect{ result ->
            when (result) {
                is Result.Failure -> {
                    progressBar.dismiss()
                    context.showToast(result.exception.message.toString())
                }
                is Result.Success -> saveGarageToDb(garage.copy(images = result.data))
                else -> {}
            }
        }
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFEEA044),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {

        Text(
            text = "Please Enter The OTP",
            color = colorResource(id = R.color.black),
            fontSize = 23.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = otp,
            onValueChange = { otp = it },
            singleLine = true,
            placeholder = {
                Text(
                    text = "Enter Your OTP",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(Icons.Filled.Notifications, "") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(.9f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                scope.launch {
                    progressBar.show()
                    authViewModel.createUser(otp, user, garage).collect {
                        if (it is Result.Success) {
                            if (user.name.isNotEmpty()) saveUserToDb(user.copy(id = it.data)) else uploadGarageImages(garage.copy(id = it.data))
                        } else if (it is Result.Failure) {
                            progressBar.dismiss()
                            context.showToast(it.exception.message.toString())
                        }
                    }
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
                text = "Continue",
                color = colorResource(id = R.color.white),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = stringResource(id = R.string.not_received_code),
            color = colorResource(id = R.color.black),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.resend_code),
            color = colorResource(id = R.color.teal_200),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable {
                    scope.launch {
                        authViewModel
                            .sendOtp(user.phoneNumber, context.getActivity())
                            .collect { result ->
                                progressBar.isVisible(result is Result.Loading)
                                when (result) {
                                    is Result.Failure -> context.showToast(result.exception.message.toString())
                                    is Result.Success -> {
                                        context.showToast(result.data)
                                    }

                                    else -> {}
                                }
                            }
                    }
                }
        )
    }
}


@Preview(device = "id:Nexus 4")
@Preview(device = "id:pixel_6_pro")
@Composable
fun VerifyOTPScreenPreview() {
    VerifyOtpScreen(navController = null, User(), Garage())
}