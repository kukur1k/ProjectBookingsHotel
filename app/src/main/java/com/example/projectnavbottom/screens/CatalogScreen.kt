package com.example.projectnavbottom.screens

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.R
import com.example.projectnavbottom.data.dao.CountryDao
import com.example.projectnavbottom.navigation.Screen
import com.example.projectnavbottom.ui.theme.ButtonTourInfo
import com.example.projectnavbottom.viewmodel.HotelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(navController: NavHostController, hotelViewModel: HotelViewModel) {

    val allHotels by hotelViewModel.allHotels.collectAsState()


    Scaffold(topBar = {
        TopAppBarMenu()
    })
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(allHotels) {hotel ->
                TourCard(
                    hotel = hotel,
                    navController = navController
                )
            }

        }
    }
}

@Composable
fun TourCard(hotel: Hotel, navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, RectangleShape)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column  {
            Box( modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
            ){
                    Image(
                        painter = painterResource(id = hotel.imgId),
                        contentDescription = "TravelImg",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
            }
            Column(modifier = Modifier
                .padding(10.dp)
            ) {
                Text(
                    text = hotel.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )


                // Настроить выбор страны
                Text(
                    text = "Страна",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "★".repeat(hotel.stars),
                        color = Color(0xFFFFD700),
                        fontSize = 14.sp
                    )
                    Text(
                        text = " (${hotel.stars} звезд)",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp),
                        color = Color.Black,
                        softWrap = false
                    )
                }


                ButtonTourInfo(
                    onClick = {
                        navController.navigate(Screen.TourInfo.passId(hotel.id))
                    }
                ){
                    Text(
                        text = "Подробнее",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMenu() {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color(0xFF1F19D9),
        ),
        title = {
            Text("Каталог", fontWeight = FontWeight.SemiBold)
        },
        modifier = Modifier
            .shadow(10.dp, RectangleShape)
            .clip(RoundedCornerShape(10.dp)),
        actions = {
            BadgedBox(
                badge =  { Badge { Text("!") } }
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Search")
                }
            }

            IconButton(onClick = {expanded = true}) {
                Icon(
                    imageVector = Icons.Default.MoreVert, contentDescription = "sett"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ){
                DropdownMenuItem(text = {Text("сортировка")},
                    onClick = {expanded = false})
                DropdownMenuItem(text = {Text("фильтрация")},
                    onClick = {expanded = false})
            }


        }

    )
}


//@Preview(showBackground = true)
//@Composable
//fun CatalogScreenPreview() {
//    CatalogScreen(navController = rememberNavController())
//}