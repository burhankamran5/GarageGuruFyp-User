package com.bkcoding.garagegurufyp_user.utils

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

fun isValidText(text: String): Boolean {
    return text.matches(Regex("[a-zA-Z ]+"))
}