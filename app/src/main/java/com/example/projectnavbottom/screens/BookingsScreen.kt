package com.example.projectnavbottom.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectnavbottom.R
import com.example.projectnavbottom.ui.theme.ReBookingButton

@Composable
fun BookingsScreen() {
    Scaffold()
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item { MyBookingCard(R.drawable.hotel1,
                "Grand Mediterranea Resort & Spa",
                "1 окт - 11 окт.",
                "€ 148,50") }
            item { MyBookingCard(R.drawable.hotel2,
                "Family hotel Marrton",
                "2 июн - 11 июн.",
                "€ 201,50") }
            item { MyBookingCard(R.drawable.hotel3,
                "Hotel Marriot Batumi",
                "4 июн - 14 июн.",
                "€ 1005,00") }
        }
    }
}


@Composable
fun MyBookingCard(idImg: Int, title: String, date: String, cost: String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, RectangleShape)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            Row() {
                Box( modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                ){
                    Image(
                        painter = painterResource(id = idImg),
                        contentDescription = "TravelImg",
                        modifier = Modifier
                            .width(110.dp)
                            .background(color = Color.White)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                Column(modifier = Modifier
                    .padding(10.dp)
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Row(modifier = Modifier.padding(top = 10.dp)) {
                        Text(
                            text = date,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Text(
                            text = cost,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 3.dp)
                        )

                    }
                    Text(
                        text = "Завершено",
                        fontSize = 16.sp,
                        color = Color(0xFFBAB6B6),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )

                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color(0xFFBAB6B6)))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start) {
                ReBookingButton(
                    onClick = {}
                ){
                    Row(modifier = Modifier.padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.refresh),
                            contentDescription = "TravelImg",
                            modifier = Modifier
                                .width(50.dp)
                                .background(color = Color.White)
                                .padding(2.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Text(
                            text = "Забронировать жилье снова",
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    }

                }

            }

        }

    }
}



@Preview(showBackground = true)
@Composable
fun BookingsPreview() {
    BookingsScreen()
}