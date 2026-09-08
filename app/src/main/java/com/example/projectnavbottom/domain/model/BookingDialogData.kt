package com.example.projectnavbottom.domain.model

data class BookingDialogData(
    val hotelId: Int? = null,
    val totalPrice: Double = 0.0,
    val startDate: String = "",
    val endDate: String = "",
    val countGuestAdult: Int = 0,
    val countGuestChild: Int = 0
)