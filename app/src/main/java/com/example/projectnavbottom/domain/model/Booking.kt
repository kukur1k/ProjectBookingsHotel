package com.example.projectnavbottom.domain.model

data class Booking(
    val id: Int = 0,
    val hotelId: Int,
    val totalPrice: Double,
    val startDate: String,
    val endDate: String,
    val countGuestAdult: Int,
    val countGuestChild: Int
)