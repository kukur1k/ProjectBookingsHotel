package com.example.projectnavbottom.viewmodel.Booking

import com.example.projectnavbottom.domain.model.Booking
import com.example.projectnavbottom.domain.model.BookingDialogData
import com.example.projectnavbottom.domain.model.BookingDialogErrors

data class BookingUiState(
    val bookings: List<Booking> = emptyList(),
    val isLoading: Boolean = false,
    val error: BookingDialogErrors? = null,
    val selectedBookingId: Int? = null,
    val dialogBooking: BookingDialogData = BookingDialogData(),
)