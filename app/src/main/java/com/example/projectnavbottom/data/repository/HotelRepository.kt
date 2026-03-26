package com.example.projectnavbottom.data.repository

import androidx.annotation.WorkerThread
import com.example.dbtesting.data.dao.HotelDao
import com.example.dbtesting.data.entity.Hotel
import kotlinx.coroutines.flow.Flow

class HotelRepository(private val hotelDao: HotelDao) {

    val allHotels: Flow<List<Hotel>> = hotelDao.getAllHotels()

    @WorkerThread
    suspend fun insert(hotel: Hotel) {
        hotelDao.insert(hotel)
    }

    @WorkerThread
    suspend fun update(hotel: Hotel) {
        hotelDao.update(hotel)
    }

    @WorkerThread
    suspend fun delete(hotel: Hotel) {
        hotelDao.delete(hotel)
    }

    suspend fun getItemById(id: Int): Hotel? {
        return hotelDao.getHotelById(id)
    }
}