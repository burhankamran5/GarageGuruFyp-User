package com.bkcoding.garagegurufyp_user.dto

data class Request(
    val id: Int = 0,
    val images: List<String> = emptyList(),
    val requestTitle: String = "",
    val description: String = "",
    val status: RequestStatus = RequestStatus.OPEN,
    val location: String = "",
    val user: User = User(),
    val garage: Garage = Garage(),
    val bids: List<Bid> = emptyList(),
    ) {
    enum class RequestStatus {
        OPEN, CLOSED, PENDING
    }
}
