package com.bkcoding.garagegurufyp_user.dto

data class Bid(
    val id: Int = 0,
    val customer: Customer? = null,
    val price: String = "",
    val garages: Garage? = Garage()
)
