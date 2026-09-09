package com.example.projectnavbottom.domain.model

data class BookingDialogData(
    val hotelId: Int? = null,
    var totalPrice: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var countGuestAdult: String = "",
    var countGuestChild: String = ""
)