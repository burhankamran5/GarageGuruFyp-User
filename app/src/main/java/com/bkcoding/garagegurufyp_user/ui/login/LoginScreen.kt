package com.bkcoding.garagegurufyp_user.ui.login


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
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
import com.bkcoding.garagegurufyp_user.extensions.isVisible
import com.bkcoding.garagegurufyp_user.extensions.progressBar
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.ui.AuthViewModel
import com.bkcoding.garagegurufyp_user.ui.UserViewModel
import com.bkcoding.garagegurufyp_user.utils.isValidEmail
import io.github.rupinderjeet.kprogresshud.KProgressHUD
import kotlinx.coroutines.launch

private const val TAG = "LoginScreen"
@Composable
fun LoginScreen(
    navController: NavController,
    authVM: AuthViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    userStorageVM: UserStorageVM = hiltViewModel()
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val progressBar: KProgressHUD = remember { context.progressBar() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("") }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    fun getGarageFromDb(userId: String) {
        scope.launch {
            userViewModel.getGarageFromDb(userId).collect { result ->
                progressBar.isVisible(result is Result.Loading)
                when (result) {
                    is Result.Success -> {
                        userStorageVM.updateUserData(UserType.Garage.name, result.data.id)
                        userStorageVM.updateGaragePref(result.data)
                        navController.navigate(Screen.GarageHomeScreen.route) {
                            popUpTo(navController.graph.id)
                        }
                    }
                    is Result.Failure -> {
                        authVM.signOutUser()
                        context.showToast(result.exception.message.toString())
                    }
                    else -> {}
                }
            }
        }
    }

    fun getCustomerFromDb(userId: String){
        scope.launch {
            userViewModel.getCustomerFromDb(userId).collect { result ->
                progressBar.isVisible(result is Result.Loading)
                when (result) {
                    is Result.Success -> {
                        userStorageVM.updateUserData(UserType.Customer.name, result.data.id)
                        userStorageVM.updateCustomerPref(result.data)
                        navController.popBackStack(navController.graph.id, true)
                        navController.navigate(Screen.CustomerHomeScreen.route){
                            popUpTo(navController.graph.id)
                        }
                    }
                    is Result.Failure -> {
                        context.showToast(result.exception.message.toString())
                    }
                    else -> {}
                }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFEEA044),
                        Color(0xFFFFFFFF)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.mechanic_icon_com),
            contentDescription = "",
            modifier = Modifier.size(110.dp)
        )

        Text(
            text = "Log-in",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppinsmedium)),
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        Text(
            text = "Email",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppinsmedium)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )


        TextField(
            value = email,
            onValueChange = {
                email = it
                isEmailValid = isValidEmail(it)
            },
            isError = email.isNotEmpty() && !isValidEmail(email),
            placeholder = {
                Text(
                    text = "Enter Your Email",
                    fontFamily = FontFamily(Font(R.font.googlesansregular)),
                    fontWeight = FontWeight.Normal
                )
            },
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

        Text(
            text = "Password",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.googlesansbold)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        TextField(
            value = password,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = {
                password = it
                isValidEmail(email)
            },
            singleLine = true,
            placeholder = {
                Text(
                    text = "Enter Your Password",
                    fontFamily = FontFamily(Font(R.font.googlesansregular)),
                    fontWeight = FontWeight.Normal
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    if (passwordVisibility) {
                        Image(painter = painterResource(id = R.drawable.ic_show), contentDescription = "", modifier = Modifier.size(25.dp))
                    } else
                        Image(painter = painterResource(id = R.drawable.ic_hide), contentDescription = "")
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(.9f)
        )
        Spacer(modifier = Modifier.height(35.dp))
        UserTypeDropdown(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.9f)
                .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                .padding(10.dp)
        ) {
            userType = if (it == UserType.Customer) "Customer" else "Garage"
        }
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedButton(
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Something is Missing", Toast.LENGTH_LONG).show()
                } else if (!isEmailValid) {
                    Toast.makeText(context, "Invalid Email", Toast.LENGTH_LONG).show()
                } else if (userType.isEmpty()){
                    Toast.makeText(context, "Please select user type", Toast.LENGTH_LONG).show()
                } else{
                   scope.launch {
                       authVM.login(email,password).collect{ result ->
                           progressBar.show()
                           when(result){
                               is Result.Failure -> {
                                   progressBar.dismiss()
                                   authVM.signOutUser()
                                   context.showToast(result.exception.message.toString())
                               }
                               is Result.Loading -> {}
                               is Result.Success -> {
                                   if (userType == UserType.Customer.name){
                                       getCustomerFromDb(result.data)
                                   } else{
                                       getGarageFromDb(result.data)
                                   }
                               }
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
                text = "LOGIN",
                color = colorResource(id = R.color.white),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.googlesansbold)),
                textAlign = TextAlign.Center,
            )
        }

        Text(
            text = "Don't have a account?",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.googlesansregular)),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.black),
        )

        Text(
            text = "Sign-Up",
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.googlesansbold)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.orange50),
            modifier = Modifier
                .clickable(
                    onClick = { navController.navigate("ChooseSignUp") { launchSingleTop = true } },
                    indication = null,
                    interactionSource = interactionSource
                )
        )
    }
}

@Composable
fun UserTypeDropdown(
    modifier: Modifier = Modifier,
    onUserTypeSelected: (UserType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var dropDownText by remember { mutableStateOf("Select user type") }

    Box(modifier = modifier.clickable { expanded = true }) {
        Text(
            text = dropDownText,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                onUserTypeSelected(UserType.Customer)
                dropDownText = UserType.Customer.name
            },
                text = {
                    Text(text = stringResource(id = R.string.customer))

                })
            DropdownMenuItem(onClick = {
                expanded = false
                onUserTypeSelected(UserType.Garage)
                dropDownText = UserType.Garage.name
            },
                text = {
                    Text(text = stringResource(id = R.string.garage))

                })
        }
    }
}
enum class UserType {
    Customer,
    Garage
}



@Preview(device = "spec:parent=Nexus 4")
@Composable
fun LoginScreenPreview() {
//    LoginScreen()
}
