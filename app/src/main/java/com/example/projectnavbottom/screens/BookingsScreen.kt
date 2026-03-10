package com.example.projectnavbottom.screens

import android.content.Context
import android.provider.CalendarContract
import android.widget.DatePicker
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projectnavbottom.R
import com.example.projectnavbottom.navigation.Screen
import com.example.projectnavbottom.ui.theme.ReBookingButton
import com.example.projectnavbottom.ui.theme.StyledButton
import java.sql.Date
import java.util.Calendar

@Composable
fun BookingsScreen(navController: NavController) {
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
                "€ 148,50",
                navController = navController) }
            item { MyBookingCard(R.drawable.hotel2,
                "Family hotel Marrton",
                "2 июн - 11 июн.",
                "€ 201,50",
                navController = navController) }
            item { MyBookingCard(R.drawable.hotel3,
                "Hotel Marriot Batumi",
                "4 июн - 14 июн.",
                "€ 1005,00",
                navController = navController) }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingCard(idImg: Int, title: String, date: String, cost: String, navController: NavController){
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


                val sheetState = rememberModalBottomSheetState()
                var isSheetOpen by rememberSaveable{
                    mutableStateOf(false)
                }

//                Даты заезда и выезда
                var selectedDateIn by rememberSaveable { mutableStateOf("") }
                var selectedDateOut by rememberSaveable { mutableStateOf("") }

                if (isSheetOpen){
                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = { isSheetOpen = false }
                    ) {
                        //Переменные для DataSelecter-а
                        val context = LocalContext.current
                        val calendar = Calendar.getInstance()
                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH)
                        val day = calendar.get(Calendar.DAY_OF_MONTH)


                        Column(modifier = Modifier.padding(20.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally) {

                            //первая строка с датой заезда
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start  = 20.dp, end  = 20.dp, bottom = 8.dp),
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                //Проверка на пустоту дат, если не пустые - выводим
                                if(selectedDateIn.isNotEmpty()){
                                    Text(
                                        text = "Дата заезда: $selectedDateIn",
                                        fontSize = 16.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                else{
                                    Text(
                                        text = "Дата заезда: --/--/--",
                                        fontSize = 16.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                //Кнопка выбора даты заезда
                                StyledButton(
                                    backColor = Color.White,
                                    onClick = {
                                        android.app.DatePickerDialog(
                                            context,
                                            { _, year, month, dayOfMonth ->
                                                selectedDateIn = "$dayOfMonth.${month + 1}.$year"
                                            },
                                            year, month, day
                                        ).show()
                                    },
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.calendar),
                                        contentDescription = "SelectDate",
                                        modifier = Modifier
                                            .width(40.dp)
                                            .padding(2.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )
                                }


                            }

                            //вторая строка с датой выезда
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start  = 20.dp, end  = 20.dp, bottom = 8.dp),
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                if (selectedDateOut.isNotEmpty()) {
                                    Text(
                                        text = "Дата выезда: $selectedDateOut",
                                        fontSize = 16.sp,
                                        modifier = Modifier.weight(1f)

                                    )
                                } else {
                                    Text(
                                        text = "Дата выезда: --/--/--",
                                        fontSize = 16.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                StyledButton(
                                    backColor = Color.White,
                                    onClick = {
                                        android.app.DatePickerDialog(
                                            context,
                                            { _, year, month, dayOfMonth ->
                                                selectedDateOut = "$dayOfMonth.${month + 1}.$year"
                                            },
                                            year, month, day
                                        ).show()
                                    },
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.calendar),
                                        contentDescription = "SelectDate",
                                        modifier = Modifier
                                            .width(40.dp)
                                            .padding(2.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )


                                }
                                Spacer(modifier = Modifier.height(8.dp))

                            }









//                            Переход к отелю
                            StyledButton(onClick = {navController.navigate(Screen.TourInfo.route) },
                                backColor = Color(0xFF1F19D9)) {
                                Text(
                                    text = "Перейти к отелю",
                                    color = Color.White
                                )
                            }

                        }
                    }
                }

                ReBookingButton(
                    onClick = {
                        isSheetOpen = true
                    }
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



//@Composable
//@OptIn(ExperimentalMaterial3Api::class)
//fun showDatePicker(onDateSelected: (String) -> Unit){
//    val year: Int
//    val month: Int
//    val day: Int
//
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//    year = calendar.get(Calendar.YEAR)
//    month = calendar.get(Calendar.MONTH)
//    day = calendar.get(Calendar.DAY_OF_MONTH)
//    calendar.time = java.util.Date()
//
//    val date =  remember { mutableStateOf("") }
//    val datePickerDialog = android.app.DatePickerDialog(
//        context,
//        { _: DatePicker,
//          year: Int, month: Int, dayOfMonth: Int ->
//            val selectedDate = "$dayOfMonth | $month | $year"
//            onDateSelected(selectedDate)
//        }, year, month, day
//    )
//
//    datePickerDialog.show()
//}


@Preview(showBackground = true)
@Composable
fun BookingsPreview() {
    val navController = rememberNavController()
    BookingsScreen(navController = navController)
}