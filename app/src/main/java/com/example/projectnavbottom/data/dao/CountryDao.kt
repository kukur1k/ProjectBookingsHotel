package com.example.projectnavbottom.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.dbtesting.data.entity.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    fun getAllCountries(): Flow<List<Country>>

    @Query("SELECT * FROM country WHERE id = :id")
    suspend fun getCountryById(id: String): Country?
}