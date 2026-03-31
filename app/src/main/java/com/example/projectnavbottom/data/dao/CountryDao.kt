package com.example.projectnavbottom.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dbtesting.data.entity.Country
import com.example.dbtesting.data.entity.Hotel
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert
    suspend fun insert(country: Country)

    @Update
    suspend fun update(country: Country)

    @Delete
    suspend fun delete(country: Country)

    @Query("SELECT * FROM country")
    fun getAllCountries(): Flow<List<Country>>

    @Query("SELECT * FROM country WHERE id = :id")
    suspend fun getCountryById(id: String): Country?
}