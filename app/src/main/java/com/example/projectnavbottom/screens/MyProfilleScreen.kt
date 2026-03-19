package com.example.projectnavbottom.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projectnavbottom.R
import com.example.projectnavbottom.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyProfileScreen(navController: NavHostController) {

    var profName by remember {mutableStateOf("Profile Name")}
    var email by remember {mutableStateOf("example@mail.ru")}
    var phone by remember {mutableStateOf("+7 *** *** ** **")}

    var newProfName by remember {mutableStateOf("")}
    var newEmail by remember {mutableStateOf("")}
    var newPhone by remember {mutableStateOf("")}


    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable{
        mutableStateOf(false)
    }


    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false }
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = newProfName,
                    onValueChange = {newProfName = it},
                    label = { Text("имя профиля") }

                )
                OutlinedTextField(
                    value = newEmail,
                    onValueChange = {newEmail = it},
                    label = { Text("email") }

                )
                OutlinedTextField(
                    value = newPhone,
                    onValueChange = {newPhone = it},
                    label = { Text("телефон") }

                )

                Row(modifier = Modifier.padding(top = 20.dp)) {
                    //Сохраняем новые данные
                    Button(
                        onClick = {
                            profName = newProfName
                            email = newEmail
                            phone = newPhone
                            isSheetOpen = false
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Сохранить")
                    }
                    Button(
                        onClick = {
                            isSheetOpen = false
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Отмена")
                    }
                }


            }
        }
    }


    Scaffold()
    { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()) {

                Box(modifier = Modifier
                    .fillMaxWidth()){
                    Box(modifier = Modifier
                        .fillMaxWidth()){
                        Image(
                            painter = painterResource(id = R.drawable.greenback),
                            contentDescription = "Pattern",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .clip(RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)),
                            contentScale = ContentScale.FillBounds
                        )



                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 50.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            //кнопка для редактирования
                            IconButton(onClick =
                                {
                                    newProfName = profName
                                    newPhone = phone
                                    newEmail = email
                                    isSheetOpen = true },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .height(40.dp)
                                    .width(40.dp)
                                    .shadow(10.dp, RectangleShape)
                                    .background(Color.White)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Create,
                                    contentDescription = "ChangData"
                                )
                            }

                            //кнопка для смены аккаунта
                            IconButton(onClick = {navController.navigate(Screen.Login.route)},
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .height(40.dp)
                                    .width(40.dp)
                                    .shadow(10.dp, RectangleShape)
                                    .background(Color.White)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ExitToApp,
                                    contentDescription = "ChangeAccount"
                                )
                            }
                        }
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.profiletestphoto),
                                contentDescription = "AppLoginImage",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(CircleShape)
                                    .border(color = Color.White, width = 3.dp, shape = CircleShape )

                            )
                            Text(
                                text = profName,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        }
                    }

                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp)
                    .offset(y = (-100).dp)
                    .shadow(10.dp, RoundedCornerShape(60.dp))
                    .clip(RoundedCornerShape(60.dp))
                    .background(Color.White)
                    .height(300.dp)
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            Column(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(topStart = 60.dp,
                                        topEnd = 60.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                                    .background(Color(0xFFE1FFD6))
                                    .padding(5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.settings),
                                    contentDescription = "AppLoginImage",
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(20.dp))

                                )
                                Text(
                                    text = "Settings",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(topStart = 60.dp,
                                        topEnd = 60.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                                    .background(Color(0xFFE1FFD6))
                                    .padding(5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.bell),
                                    contentDescription = "AppLoginImage",
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(20.dp))

                                )
                                Text(
                                    text = "Notification",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(topStart = 60.dp,
                                        topEnd = 60.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                                    .background(Color(0xFFE1FFD6))
                                    .padding(5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.payment),
                                    contentDescription = "AppLoginImage",
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(20.dp))

                                )
                                Text(
                                    text = "Payment",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }

                        Text(
                            "Личные данные",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 10.dp)
                        )



                        Column(modifier = Modifier.padding(top = 10.dp)) {

                            DataRow( "ID-No", "987346-22")
                            DataRow( "email:", email)
                            DataRow("Phone:", phone)



                        }




                    }






                }

        }
    }
}


@Composable
fun DataRow(textTitle: String, text: String){
    Row() {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .shadow(10.dp, RoundedCornerShape(60.dp))
                .clip(RoundedCornerShape(60.dp))
                .background(Color.White)
                .height(40.dp)
                .width(80.dp)) {
            Text(
                text = textTitle,
                fontWeight = FontWeight.Bold
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp,)
                .shadow(10.dp, RoundedCornerShape(60.dp))
                .clip(RoundedCornerShape(60.dp))
                .background(Color.White)
                .height(40.dp)
                .width(200.dp)) {
            Text(
                text = text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyProfileScreenPreview() {
    MyProfileScreen(navController = rememberNavController())
}


