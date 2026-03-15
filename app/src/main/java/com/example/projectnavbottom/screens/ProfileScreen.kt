package com.example.projectnavbottom.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projectnavbottom.R
import com.example.projectnavbottom.navigation.Screen
import com.example.projectnavbottom.screens.viewmodel.LoginScreenViewModel
import com.example.projectnavbottom.ui.theme.StyledButton



@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: LoginScreenViewModel = viewModel()
){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.loginbackimg),
            contentDescription = "loginBack",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "BookingIsland",
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(top = 100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "AppLoginImage",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 15.dp)
            )

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = viewModel::updateEmail,
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Outlined.Email),
                        contentDescription = null
                    )
                },
                modifier = Modifier.padding(top = 130.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White),
                placeholder = {
                    Text(text = "Введите логин")
                }
            )
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = viewModel::updatePassword,
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                        contentDescription = null
                    )
                },
                modifier = Modifier.padding(top = 10.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text(text = "Введите пароль")
                }
            )

            StyledButton(
                onClick = {},
                modifier = Modifier.padding(top = 30.dp),
                backColor = Color.White
            ){
                Text(
                    text = "Войти",
                    fontSize = 19.sp,
                    color = Color.Black
                )
            }
            Text(
                text = "Еще не зарегистрированны? Зарегистрируйтесь",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 60.dp)
                    .clickable  {
                        navController.navigate(Screen.Register.route)
                    }
            )
        }
    }



}


@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}