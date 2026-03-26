package com.example.dbtesting.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dbtesting.data.entity.Booking
import com.example.dbtesting.data.entity.Hotel
import kotlinx.coroutines.flow.Flow


@Dao
interface BookingDao{
    @Insert
    suspend fun insert(booking: Booking)

    @Update
    suspend fun update(booking: Booking)

    @Delete
    suspend fun delete(booking: Booking)

    @Query("SELECT * FROM booking ORDER BY id DESC")
    fun getAllIBookings(): Flow<List<Booking>>

    @Query("SELECT * FROM booking WHERE id = :id")
    suspend fun getBookingById(id: Int): Booking?
}
