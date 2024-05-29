package com.bkcoding.garagegurufyp_user.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

fun isValidText(text: String): Boolean {
    return text.matches(Regex("[a-zA-Z ]+"))
}

@Composable
fun CityDropDown(
    modifier: Modifier = Modifier,
    onUserCitySelected: (City) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var dropDownText by remember { mutableStateOf("Select Your City") }

    Box(modifier = modifier.clickable { expanded = true }) {
        androidx.compose.material.Text(
            text = dropDownText,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        androidx.compose.material.Icon(
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
                onUserCitySelected(City.Lahore)
                dropDownText = City.Lahore.name
            },
                text = {
                    androidx.compose.material.Text(text =  City.Lahore.name)

                })
            DropdownMenuItem(onClick = {
                expanded = false
                onUserCitySelected(City.Islamabad)
                dropDownText = City.Islamabad.name
            },
                text = {
                    androidx.compose.material.Text(text =  City.Islamabad.name)

                })

            DropdownMenuItem(onClick = {
                expanded = false
                onUserCitySelected(City.Karachi)
                dropDownText = City.Karachi.name
            },
                text = {
                    androidx.compose.material.Text(text =  City.Karachi.name)

                })

            DropdownMenuItem(onClick = {
                expanded = false
                onUserCitySelected(City.Multan)
                dropDownText = City.Multan.name
            },
                text = {
                    androidx.compose.material.Text(text =  City.Multan.name)

                })
        }
    }
}

enum class City {
    Lahore,
    Islamabad,
    Karachi,
    Multan
}