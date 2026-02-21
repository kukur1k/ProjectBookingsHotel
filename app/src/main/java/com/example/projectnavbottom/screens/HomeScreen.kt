package com.example.projectnavbottom.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Label
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectnavbottom.navigation.Screen
import com.example.projectnavbottom.ui.theme.ButtonSearchHome
import com.example.projectnavbottom.ui.theme.ButtonTourInfo


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
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Отели и квартиры",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F19D9),
                    modifier = Modifier
                        .padding(bottom = 36.dp)
                )
                SearchCard()
            }
        }

    }
}

@Composable
fun SearchCard(){
    var text by remember {mutableStateOf("")}
    Column(
        modifier = Modifier
            .shadow(10.dp, RectangleShape)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        TextField(label = { Text("Выберите направление") },
            value = text,
            onValueChange = {text = it},
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(label = { Text("Дата заезда") },
                value = text,
                onValueChange = {text = it},
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
            )
            TextField(label = { Text("Дата выезда") },
                value = text,
                onValueChange = {text = it},
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))

            )
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