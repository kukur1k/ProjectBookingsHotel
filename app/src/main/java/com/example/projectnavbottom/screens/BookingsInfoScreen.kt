package com.example.projectnavbottom.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectnavbottom.R
import com.example.projectnavbottom.navigation.Screen

@Composable
fun BookingsInfoScreen(onBack: () -> Boolean) {
    Scaffold()
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                BookingsInfoDetails(R.drawable.hotel2,
                    "Family hotel Marrton",
                    "2 июн - 11 июн.",
                    "€ 201,50")
            }
        }
    }
}

@Composable
fun BookingsInfoDetails(idImg: Int, title: String, date: String, cost: String) {
    Column() {
        Image(
            painter = painterResource(id = idImg),
            contentDescription = "TravelImg",
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "★★★★★",
                color = Color(0xFFFF8C00),
                fontSize = 20.sp
            )
            Row() {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .width(200.dp)
                )
                Spacer(modifier = Modifier.width(100.dp))
                Box(){
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "TravelImg",
                        modifier = Modifier
                            .width(60.dp)
                            .background(color = Color.White)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ){
                        Text(
                            text = "8,9",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.White,
                        )
                    }
                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFFFFFFF))
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = date + " 2026",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "2 зрослых, 1 ребёнок",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

            }


        }

        Spacer(modifier = Modifier.height(20.dp))


        Column(modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFFFFFFF))
            .fillMaxWidth()
            .padding(10.dp)) {  }


    }

}

@Preview(showBackground = true)
@Composable
fun BookingsInfoScreen() {
    BookingsInfoScreen({true})
}
