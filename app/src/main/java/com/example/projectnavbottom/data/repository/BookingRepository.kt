package com.example.projectnavbottom.data.repository

import androidx.annotation.WorkerThread
import com.example.dbtesting.data.dao.BookingDao
import com.example.dbtesting.data.dao.HotelDao
import com.example.dbtesting.data.entity.Booking
import com.example.dbtesting.data.entity.Hotel
import kotlinx.coroutines.flow.Flow

class BookingRepository(private val bookingDao: BookingDao) {

    val allBookings: Flow<List<Booking>> = bookingDao.getAllIBookings()

    @WorkerThread
    suspend fun insert(booking: Booking) {
        bookingDao.insert(booking)
    }

    @WorkerThread
    suspend fun update(booking: Booking) {
        bookingDao.update(booking)
    }

    @WorkerThread
    suspend fun delete(booking: Booking) {
        bookingDao.delete(booking)
    }

    suspend fun getBookingById(id: Int): Booking? {
        return bookingDao.getBookingById(id)
    }
}