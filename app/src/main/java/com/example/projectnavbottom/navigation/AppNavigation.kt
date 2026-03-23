package com.example.projectnavbottom.navigation


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectnavbottom.screens.BookingsInfoScreen
import com.example.projectnavbottom.screens.BookingsScreen
import com.example.projectnavbottom.screens.CatalogScreen
import com.example.projectnavbottom.screens.HomeScreen
import com.example.projectnavbottom.screens.LoginScreen
import com.example.projectnavbottom.screens.MyProfileScreen
import com.example.projectnavbottom.screens.RegisterScreen
import com.example.projectnavbottom.screens.SplashScreen
import com.example.projectnavbottom.screens.TourInfoScreen


@Composable
fun AppNavigation(navController: NavHostController){

    NavHost(navController = navController,
        startDestination = Screen.Splash.route,

        //анимация перехода

        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(400)
            )
        },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(400)
            )
        }

    ){
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Profile.route) {
            MyProfileScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Catalog.route) {
            CatalogScreen(navController)
        }
        composable(Screen.Bookings.route) {
            BookingsScreen(navController = navController)
        }
        composable(Screen.BookingsInfo.route) {
            BookingsInfoScreen(
                onBack = {navController.navigateUp()}
            )
        }
        composable(Screen.TourInfo.route) {
            TourInfoScreen(
                onBack = {navController.navigateUp()}
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Splash.route) {
            SplashScreen(
                {
                    navController.navigate(Screen.Home.route){
                        popUpTo(Screen.Home.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}



