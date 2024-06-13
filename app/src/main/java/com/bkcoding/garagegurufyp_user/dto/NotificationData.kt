package com.bkcoding.garagegurufyp_user.dto

data class NotificationData(
    val id: String = "",
    val from: String = "",
    val thumbnail: String = "",
    val to: String = "",
    val title: String = "",
    val description: String = "",
    val sentAt: Any = 0
)

enum class NotificationAction(val label: String){
    BID_ACCEPTED("Bid Accepted"),
    BID("Bid"),
}
