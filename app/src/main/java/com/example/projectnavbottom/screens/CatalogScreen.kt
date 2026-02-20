package com.example.projectnavbottom.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectnavbottom.HomeScreen
import com.example.projectnavbottom.R
import com.example.projectnavbottom.navigation.Screen
import com.example.projectnavbottom.ui.theme.ButtonTourInfo

@Composable
fun CatalogScreen() {
    Scaffold()
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item { TourCard() }
            item { TourCard() }
            item { TourCard() }
            item { TourCard() }
            item { TourCard() }
        }
    }
}

@Composable
fun TourCard(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF393EEA), RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row() {
            Box( modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
            ){
                    Image(
                        painter = painterResource(id = R.drawable.hotel1),
                        contentDescription = "TravelImg",
                        modifier = Modifier
                            .width(170.dp)
                            .background(color = Color.White)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
            }
            Column(modifier = Modifier
                .padding(10.dp)
            ) {
                Text(
                    text = "Grand Mediterranea Resort & Spa",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = "Турция, Анталия",
                    fontSize = 14.sp,
                    color = Color.White
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "★★★★★",
                        color = Color(0xFFFFD700),
                        fontSize = 14.sp
                    )
                    Text(
                        text = " (5 звезд)",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp),
                        color = Color.White
                    )
                }
                ButtonTourInfo(
                    onClick = {}
                ){
                    Text(
                        text = "Подробнее",
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogScreenPreview() {
    CatalogScreen()
}