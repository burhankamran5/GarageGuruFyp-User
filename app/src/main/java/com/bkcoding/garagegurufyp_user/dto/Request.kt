package com.bkcoding.garagegurufyp_user.dto

import android.net.Uri
import com.google.firebase.database.Exclude

data class Request(
    val id: String = "",
    @get:Exclude val imageUris: List<Uri> = emptyList(),
    val images: List<String> = emptyList(),
    val carModel: String = "",
    val description: String = "",
    val status: RequestStatus = RequestStatus.OPEN,
    val city: String = "",
    val customer: Customer = Customer(),
    val garage: Garage = Garage(),
    val bids: Map<String,Bid> = mapOf(),
    ) {
    enum class RequestStatus {
        OPEN, CLOSED, PENDING
    }
}