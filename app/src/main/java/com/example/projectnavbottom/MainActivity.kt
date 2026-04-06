package com.example.projectnavbottom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.dbtesting.data.database.AppDatabase
import com.example.dbtesting.data.entity.Country
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.data.dao.CountryDao
import com.example.projectnavbottom.data.repository.BookingRepository
import com.example.projectnavbottom.data.repository.CountryRepository
import com.example.projectnavbottom.data.repository.HotelRepository
import com.example.projectnavbottom.navigation.MainAppScaffold
import com.example.projectnavbottom.ui.theme.ProjectNavBottomTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            val database = AppDatabase.getDatabase(this@MainActivity)
            val hotelDao = database.HotelDao()
            val countryDao = database.CountryDao()

            val countrys = listOf(
                Country(id = 1, "Турция"),
                Country(id = 2, "Грузия"),
            )

            val hotels = listOf(
                Hotel(
                    id = 1,
                    title = "Grand Mediterranea Resort & Spa",
                    description = "Роскошный пятизвездочный курорт",
                    stars = 5,
                    countryId = 1,
                    imgId = R.drawable.hotel1
                ),
                Hotel(
                    id = 2,
                    title = "Family Hotel Marrton",
                    description = "Уютный семейный отель",
                    stars = 4,
                    countryId = 1,
                    imgId = R.drawable.hotel2
                ),
                Hotel(
                    id = 3,
                    title = "Hotel Marriot Batumi",
                    description = "Премиальный отель",
                    stars = 5,
                    countryId = 2,
                    imgId = R.drawable.hotel3
                )
            )


            countrys.forEach { country ->
                try {
                    countryDao.insert(country)
                } catch (e: Exception) {
                    countryDao.update(country)
                    Log.e("ERROR DB", "ERROR")
                    e.message
                }
            }

            hotels.forEach { hotel ->
                try {
                    hotelDao.insert(hotel)
                } catch (e: Exception) {
                    hotelDao.update(hotel)
                    Log.e("ERROR DB", "ERROR")
                    e.message
                }
            }
        }


        val database = AppDatabase.getDatabase(this)
        val repositorybooking = BookingRepository(database.BookingDao())
        val repositoryhotel = HotelRepository(database.HotelDao())
        val repositorycountry = CountryRepository(database.CountryDao())

        setContent {
            ProjectNavBottomTheme {
                val navController = rememberNavController()
                MainAppScaffold(navController, repositorybooking, repositoryhotel, repositorycountry )
            }
                }
            }
        }

