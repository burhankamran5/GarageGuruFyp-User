package com.bkcoding.garagegurufyp_user.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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

fun convertMillisToDate(millis: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(Date(millis))
}

@RequiresApi(Build.VERSION_CODES.O)
fun getInboxRelativeTime(firebaseTime: String): String {
    // Parse the API timestamp
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val apiDateTime = LocalDateTime.parse(firebaseTime, formatter)

    // Get the current time
    val currentDateTime = LocalDateTime.now(ZoneId.of("UTC"))

    // Calculate the difference
    val years = ChronoUnit.YEARS.between(apiDateTime, currentDateTime)
    val months = ChronoUnit.MONTHS.between(apiDateTime, currentDateTime)
    val weeks = ChronoUnit.WEEKS.between(apiDateTime, currentDateTime)
    val days = ChronoUnit.DAYS.between(apiDateTime, currentDateTime)
    val hours = ChronoUnit.HOURS.between(apiDateTime, currentDateTime)
    val minutes = ChronoUnit.MINUTES.between(apiDateTime, currentDateTime)

    return when {
        years > 0 -> "${years}y"
        months > 0 -> "${months}m"
        weeks > 0 -> "${weeks}w"
        days > 0 -> "${days}d"
        hours > 0 -> "${hours}h"
        minutes > 0 -> "${minutes}min"
        else -> "just now"
    }
}

    enum class City {
    All,
    Lahore,
    Islamabad,
    Karachi,
    Multan
}