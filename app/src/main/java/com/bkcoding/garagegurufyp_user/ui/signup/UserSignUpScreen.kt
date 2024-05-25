package com.bkcoding.garagegurufyp_user.ui.signup

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.ui.AuthViewModel
import com.bkcoding.garagegurufyp_user.dto.Customer
import com.bkcoding.garagegurufyp_user.extensions.getActivity
import com.bkcoding.garagegurufyp_user.extensions.isVisible
import com.bkcoding.garagegurufyp_user.extensions.progressBar
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.utils.isValidEmail
import com.bkcoding.garagegurufyp_user.utils.isValidText
import io.github.rupinderjeet.kprogresshud.KProgressHUD
import kotlinx.coroutines.launch

@Composable
fun UserSignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    onChangeUser: (Customer) -> Unit
) {

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var passwordVisibility: Boolean by rememberSaveable { mutableStateOf(false) }
    var isEmailValid by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val progressBar: KProgressHUD = remember { context.progressBar() }

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
            text = "Create your account",
            color = colorResource(id = R.color.black),
            fontSize = 23.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .fillMaxWidth()
        )

        TextField(
            value = name,
            onValueChange = { newName ->
                if (isValidText(newName)) {
                    name = newName
                }
            },
            placeholder = {
                Text(
                    text = "Enter Your Name",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            leadingIcon = { Icon(Icons.Filled.Person, "") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )



        TextField(
            value = email,
            onValueChange = {
                email = it
                isEmailValid = isValidEmail(it)
            },
            placeholder = {
                Text(
                    text = "Enter Your Email",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            isError = email.isNotEmpty() && !isValidEmail(email),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Filled.Email, "") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = "Enter Password",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    if (passwordVisibility) {
                        Image(painter = painterResource(id = R.drawable.ic_show), contentDescription = "", modifier = Modifier.size(25.dp))
                    } else
                        Image(painter = painterResource(id = R.drawable.ic_hide), contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = "Confirm Password",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    if (passwordVisibility) {
                        Image(painter = painterResource(id = R.drawable.ic_show), contentDescription = "", modifier = Modifier.size(25.dp))
                    } else
                        Image(painter = painterResource(id = R.drawable.ic_hide), contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            placeholder = {
                Text(
                    text = "3XXXXXXXXX",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = { Icon(Icons.Filled.Phone, "") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = city,
            onValueChange = { city = it },
            placeholder = {
                Text(
                    text = "Enter Your City",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = { Icon(Icons.Filled.Home, "") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                val customer = Customer("", name, email, city, phoneNumber, password, confirmPassword)
                if (isInputValid(context, customer)) {
                    scope.launch {
                        authViewModel.sendOtp(phoneNumber, context.getActivity()).collect{ result ->
                            progressBar.isVisible(result is Result.Loading)
                            when(result){
                                is Result.Failure -> context.showToast(result.exception.message.toString())
                                is Result.Success -> {
                                    context.showToast(result.data)
                                    onChangeUser(customer)
                                    navController.navigate(Screen.VerifyOtpScreen.route) { launchSingleTop = true }
                                }
                                else -> {}
                            }
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
    }
}

private fun isInputValid(context: Context, customer: Customer): Boolean {
    var isInputValid = false
    when {
        customer.name.isEmpty() || customer.email.isEmpty() || customer.password.isEmpty() || customer.confirmPassword.isEmpty() || customer.city.isEmpty() || customer.phoneNumber.isEmpty() -> {
           context.showToast("Something is Missing")
        }
        !isValidEmail(customer.email) -> context.showToast("Invalid Email")
        customer.password != customer.confirmPassword -> context.showToast("Password doesn't match")
        customer.password.length < 6 -> context.showToast("Password too short")
        customer.phoneNumber.length != 10 ->context.showToast("Phone# length should be 10 excluding 0")
        else -> isInputValid = true
    }
    return isInputValid
}

@Preview(device = "id:Nexus 4")
@Preview(device = "id:pixel_6_pro")
@Composable
fun PreviewSignUp() {
//    UserSignUpScreen()
}