package com.example.projectnavbottom.domain.model

data class BookingDialogErrors(
    var startDateError: String? = null,
    var endDateError: String? = null,
    var totalPriceError: String? = null,
    var adultCountError: String? = null
)