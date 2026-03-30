package com.example.projectnavbottom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.dbtesting.data.database.AppDatabase
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.data.repository.BookingRepository
import com.example.projectnavbottom.data.repository.HotelRepository
import com.example.projectnavbottom.navigation.MainAppScaffold
import com.example.projectnavbottom.screens.CatalogScreen
import com.example.projectnavbottom.screens.TourInfoScreen
import com.example.projectnavbottom.ui.theme.ProjectNavBottomTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            val database = AppDatabase.getDatabase(this@MainActivity)
            val hotelDao = database.HotelDao()

            // Добавляем отели
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

            hotels.forEach { hotel ->
                try {
                    hotelDao.insert(hotel)
                } catch (e: Exception) {
                    hotelDao.update(hotel)
                }
            }
        }


        val database = AppDatabase.getDatabase(this)
        val repositorybooking = BookingRepository(database.BookingDao())
        val repositoryhotel = HotelRepository(database.HotelDao())

        setContent {
            ProjectNavBottomTheme {
                val navController = rememberNavController()
                MainAppScaffold(navController, repositorybooking, repositoryhotel)
            }
                }
            }
        }

