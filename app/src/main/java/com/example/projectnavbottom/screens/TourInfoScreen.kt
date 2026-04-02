package com.example.projectnavbottom.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.R
import com.example.projectnavbottom.ui.components.BookingInputDialog
import com.example.projectnavbottom.ui.theme.StyledButton
import com.example.projectnavbottom.viewmodel.BookingViewModel
import com.example.projectnavbottom.viewmodel.HotelViewModel

@Composable
fun TourInfoScreen(
    onBack: () -> Boolean,
    bookingviewModel: BookingViewModel,
    hotelViewModel: HotelViewModel,
    hotelId: Int
)
{

    val allHotels by hotelViewModel.allHotels.collectAsState()
    val hotel = allHotels.find {it.id == hotelId}

    if (hotel != null){

        Scaffold()
        { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    CardInfoScreen(hotel = hotel, viewModel = bookingviewModel)
                }
            }
        }

    }else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Отель не найден")
        }
    }


}

@Composable
fun CardInfoScreen(hotel: Hotel,
                   viewModel: BookingViewModel) {

    var showDialog by remember { mutableStateOf(false) }

    Column() {
        Image(
            painter = painterResource(id = hotel.imgId),
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
                text = "★".repeat(hotel.stars),
                color = Color(0xFFFF8C00),
                fontSize = 20.sp
            )
            Row() {
                Text(
                    text = hotel.title,
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
                        text = "17.07" + " 2026",
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
                Spacer(modifier = Modifier.width(55.dp))
                Image(
                    painter = painterResource(id = R.drawable.lupa),
                    contentDescription = "TravelImg",
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .width(60.dp)
                        .background(color = Color.White)
                        .padding(2.dp)

                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            StyledButton(
                backColor = Color(0xFF1F19D9),
                onClick = { showDialog = true }
            ) {
                Text(
                    text = "Забронировать",
                    fontSize = 16.sp
                )
            }
        }


        if (showDialog) {
            BookingInputDialog(
                title = "Создание брони",
                onDismiss = { showDialog = false },
                onConfirm = {hotelId, prc, startDate, endDate, countGuestAdult, countGuestChild ->
                    viewModel.insertBooking( hotel.id, prc, startDate, endDate, countGuestAdult, countGuestChild)
                }
            )
        }


        Spacer(modifier = Modifier.height(20.dp))


        ReviewsSwiper()


    }

}

@Composable
fun ReviewsSwiper(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)

    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Отзывы",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
    LazyRow(modifier = Modifier.padding(10.dp)) {
        item { Review("Alexandra",
            "Останавливались в этом отеле на неделю — остались в " +
                    "полном восторге! Расположение просто идеальное: до моря идти " +
                    "буквально 2–3 минуты. Пляж чистый, есть шезлонги и зонты. Номера " +
                    "уютные, всё необходимое есть. Персонал приветливый, чувствуется " +
                    "забота о гостях. Обязательно вернёмся ещё!") }
        item { Review("Ivan",
            "Выбрали этот отель из‑за близости к морю — и не прогадали! До пляжа идти меньше пяти минут по ухоженной дорожке, что особенно удобно с детьми. Номер был чистым и просторным, с балконом и видом на море. Отдельно хочу отметить персонал: все очень вежливые и отзывчивые, помогли с организацией экскурсий и подсказали лучшие места для ужина. Питание в ресторане отеля тоже порадовало — разнообразное и вкусное. Из плюсов ещё отмечу наличие бассейна и зоны отдыха с гамаками. Однозначно рекомендую!") }
        item { Review("James",
            "Останавливались в этом отеле на неделю — остались в " +
                    "полном восторге! Расположение просто идеальное: до моря идти " +
                    "буквально 2–3 минуты. Пляж чистый, есть шезлонги и зонты. Номера " +
                    "уютные, всё необходимое есть. Персонал приветливый, чувствуется " +
                    "забота о гостях. Обязательно вернёмся ещё!") }
    }
}

@Composable
fun Review(name: String, description: String){
    var isExpanded by remember{
        mutableStateOf(false)
    }
    Box(modifier = Modifier.padding(10.dp)){
        Column(modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .width(250.dp)
            .shadow(10.dp, RectangleShape)
            .background(Color.White)

        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .width(100.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "TravelImg",
                        modifier = Modifier
                            .width(30.dp)
                            .background(color = Color.White)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "8,6",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White,
                        )
                    }
                }
            }
            Box(modifier = Modifier.padding(10.dp)){
                Column(modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        isExpanded = !isExpanded
                    }) {
                    Text(
                        text = description,
                        fontSize = 13.sp,
                        color = Color(0xFF6F6B6B),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 9
                    )
                }

            }
            }

    }

}


//@Preview(showBackground = true)
//@Composable
//fun TourInfoScreenPreview() {
//    TourInfoScreen({true})
//}