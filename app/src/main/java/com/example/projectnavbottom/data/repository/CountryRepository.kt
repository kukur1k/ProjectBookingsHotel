package com.example.projectnavbottom.data.repository

import androidx.annotation.WorkerThread
import com.example.dbtesting.data.dao.BookingDao
import com.example.dbtesting.data.dao.HotelDao
import com.example.dbtesting.data.entity.Booking
import com.example.dbtesting.data.entity.Country
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.data.dao.CountryDao
import kotlinx.coroutines.flow.Flow

class CountryRepository(private val countryDao: CountryDao) {

    val allCountries: Flow<List<Country>> = countryDao.getAllCountries()

    @WorkerThread
    suspend fun insert(country: Country) {
        countryDao.insert(country)
    }

    @WorkerThread
    suspend fun update(country: Country) {
        countryDao.update(country)
    }

    @WorkerThread
    suspend fun delete(country: Country) {
        countryDao.delete(country)
    }

    suspend fun getCountryById(id: Int): Country? {
        return countryDao.getCountryById(id)
    }
}