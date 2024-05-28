package com.bkcoding.garagegurufyp_user.dto

data class Bid(
    val id: Int = 0,
    val customer: Customer,
    val price: String = "",
    val garages:List<Garage> = emptyList()
    )
