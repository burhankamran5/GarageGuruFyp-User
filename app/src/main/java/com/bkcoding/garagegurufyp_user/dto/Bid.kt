package com.bkcoding.garagegurufyp_user.dto

data class Bid(
    val id: String = "",
    val customer: Customer? = null,
    val price: String = "",
    val garage: Garage? = null,
    val bidStatus: BidStatus = BidStatus.PENDING
)

enum class BidStatus {
    PENDING, ACCEPTED, DECLINED
}