package com.example.projectnavbottom.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyProfileScreen(navController: NavHostController) {
    Scaffold()
    { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
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
                            .padding(start = 20.dp, end = 20.dp, top = 30.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            //кнопка для редактирования
                            IconButton(onClick = {},
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
                            IconButton(onClick = {},
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
                            .padding(top = 80.dp),
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
                                text = "Profile Name",
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

                    }
                }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyProfileScreenPreview() {
    MyProfileScreen(navController = rememberNavController())
}


