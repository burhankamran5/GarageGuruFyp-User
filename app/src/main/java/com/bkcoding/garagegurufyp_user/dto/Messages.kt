package com.bkcoding.garagegurufyp_user.dto

data class Messages(
    var from: String = "",
    var message: String = "",
    var type: String = "",
    var time: Long = 0,
    var seen: Boolean = false
)