package com.example.projectnavbottom.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectnavbottom.navigation.Screen
import com.example.projectnavbottom.ui.theme.ButtonSearchHome
import com.example.projectnavbottom.ui.theme.ButtonTourInfo
import com.example.projectnavbottom.R
import java.util.Calendar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold{ paddingValues ->
        Box(
            modifier = Modifier
                .background(Color(0xFF8DF882))
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            LaunchedEffect(Unit) {
                println("hotel1 ID: ${R.drawable.hotel1}")
                println("hotel2 ID: ${R.drawable.hotel2}")
                println("hotel3 ID: ${R.drawable.hotel3}")
            }
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Отели и квартиры",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F19D9),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .padding(top = 30.dp)
                )
                SearchCard()
            }
        }

    }
}

@Composable
fun SearchCard(){
    var textLand by remember {mutableStateOf("")}
    var textCount by remember {mutableStateOf("")}
    var expanded by remember { mutableStateOf(false) }
    var expandedCount by remember { mutableStateOf(false) }


    val Lands = listOf(
        "Турция",
        "Египет",
        "Таиланд",
        "ОАЭ",
        "Германия",
        "Франция",
        "Италия"
    )

    val CountGuest = listOf(
        "1", "2", "3", "4"
    )

    Column(
        modifier = Modifier
            .shadow(10.dp, RectangleShape)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = textLand,
                onValueChange = {},
                label = { Text("Выберите направление") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Выбрать направление"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Lands.forEach { land ->
                    DropdownMenuItem(
                        text = { Text(text = land) },
                        onClick = {
                            textLand = land
                            expanded = false
                        }
                    )
                }
            }

        }

        //Переменные для DataSelecter-а
        val context = LocalContext.current
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        var selectedDateIn by rememberSaveable { mutableStateOf("") }
        var selectedDateOut by rememberSaveable { mutableStateOf("") }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = selectedDateIn,
                onValueChange = { },
                label = { Text(text = "Дата заезда", fontSize = 15.sp) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { android.app.DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            selectedDateIn = "$dayOfMonth.${month + 1}.$year"
                        },
                        year, month, day
                    ).show() }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier
                    .weight(1f)

            )

            OutlinedTextField(
                value = selectedDateOut,
                onValueChange = { },
                label = { Text("Дата выезда", fontSize = 15.sp) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { android.app.DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            selectedDateOut = "$dayOfMonth.${month + 1}.$year"
                        },
                        year, month, day
                    ).show() }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier
                    .weight(1f)

            )
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = textCount,
                onValueChange = {},
                label = { Text(text = "Количество проживающих") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expandedCount = true }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Количество проживающих"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedCount = true }
            )

            DropdownMenu(
                expanded = expandedCount,
                onDismissRequest = { expandedCount = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                CountGuest.forEach { count ->
                    DropdownMenuItem(
                        text = { Text(text = count) },
                        onClick = {
                            textCount = count
                            expandedCount = false
                        }
                    )
                }
            }

        }

        ButtonSearchHome(
            onClick = {}
        ){
            Text(
                text = "Найти",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}