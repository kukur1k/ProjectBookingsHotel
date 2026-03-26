package com.example.dbtesting.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dbtesting.data.entity.Hotel
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao{
    @Insert
    suspend fun insert(hotel: Hotel)

    @Update
    suspend fun update(hotel: Hotel)

    @Delete
    suspend fun delete(hotel: Hotel)

    @Query("SELECT * FROM hotel ORDER BY id DESC")
    fun getAllHotels(): Flow<List<Hotel>>

    @Query("SELECT * FROM hotel WHERE id = :id")
    suspend fun getHotelById(id: Int): Hotel?
}