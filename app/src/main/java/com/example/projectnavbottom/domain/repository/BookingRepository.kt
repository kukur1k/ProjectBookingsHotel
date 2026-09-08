package com.example.projectnavbottom.domain.repository

import com.example.projectnavbottom.domain.model.Booking
import com.example.projectnavbottom.domain.model.Hotel
import kotlinx.coroutines.flow.Flow

interface BookingRepository {
    fun getBookings(): Flow<List<Booking>>
    suspend fun  insertBooking(booking: Booking) // принимает доменную модельЮ так как репозиторий работает с данными,
    // и не должен заниматьс поиском (Single)
    suspend fun  updateBooking(booking: Booking)
    suspend fun  deleteBooking(booking: Booking)
}