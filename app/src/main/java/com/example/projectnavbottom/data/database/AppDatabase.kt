package com.example.dbtesting.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dbtesting.data.dao.BookingDao
import com.example.dbtesting.data.dao.HotelDao
import com.example.dbtesting.data.entity.Booking
import com.example.dbtesting.data.entity.Country
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.data.dao.CountryDao


@Database(
    entities = [Country::class, Hotel::class, Booking::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun HotelDao(): HotelDao
    abstract fun BookingDao(): BookingDao

    abstract fun CountryDao(): CountryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(

                    context.applicationContext,

                    AppDatabase::class.java,

                    "hotels_database"
                ).build()

                INSTANCE = instance

                instance

            }

        }

    }

}