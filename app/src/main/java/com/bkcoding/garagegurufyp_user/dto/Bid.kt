package com.bkcoding.garagegurufyp_user.dto

data class Bid(
    val id: Int = 0,
    val user: User,
    val price: String = "",
    val garages:List<Garage> = emptyList()
    )
