package com.example.projectnavbottom.data.repository

import com.example.dbtesting.data.dao.BookingDao
import com.example.projectnavbottom.domain.model.Booking
import com.example.projectnavbottom.domain.model.Hotel
import com.example.projectnavbottom.domain.repository.BookingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookingRepositoryImpl(private val bookingDao: BookingDao): BookingRepository {

    override fun getBookings(): Flow<List<com.example.projectnavbottom.domain.model.Booking>> {
        return bookingDao.getAllIBookings()
            .map { entities  ->
                entities.map { entity -> entity.toDomain() }
            }
    }

    override suspend fun insertBooking(booking: com.example.projectnavbottom.domain.model.Booking) {
        val entity = booking.toEntity() // маппингуем и инсертим
        bookingDao.insert(entity)
    }

    override suspend fun updateBooking(booking: com.example.projectnavbottom.domain.model.Booking) {
        val entity = booking.toEntity() // маппингуем и update
        bookingDao.update(entity)
    }

    override suspend fun deleteBooking(booking: com.example.projectnavbottom.domain.model.Booking) {
        val entity = booking.toEntity() // маппингуем и delete
        bookingDao.delete(entity)
    }


}

// маппер
fun  Booking.toEntity() = com.example.dbtesting.data.entity.Booking(
    id = this.id,
    hotelId = this.hotelId,
    totalPrice = this.totalPrice,
    startDate = this.startDate,
    endDate = this.endDate,
    countGuestAdult = this.countGuestAdult,
    countGuestChild = this.countGuestChild

)

fun  com.example.dbtesting.data.entity.Booking.toDomain() = Booking(
    id = this.id,
    hotelId = this.hotelId,
    totalPrice = this.totalPrice,
    startDate = this.startDate,
    endDate = this.endDate,
    countGuestAdult = this.countGuestAdult,
    countGuestChild = this.countGuestChild

)