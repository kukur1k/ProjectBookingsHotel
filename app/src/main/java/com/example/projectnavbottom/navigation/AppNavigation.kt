package com.example.projectnavbottom.navigation


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.projectnavbottom.screens.BookingsInfoScreen
import com.example.projectnavbottom.screens.BookingsScreen
import com.example.projectnavbottom.screens.CatalogScreen
import com.example.projectnavbottom.screens.HomeScreen
import com.example.projectnavbottom.screens.LoginScreen
import com.example.projectnavbottom.screens.MyProfileScreen
import com.example.projectnavbottom.screens.RegisterScreen
import com.example.projectnavbottom.screens.SplashScreen
import com.example.projectnavbottom.screens.TourInfoScreen
import com.example.projectnavbottom.viewmodel.BookingViewModel
import com.example.projectnavbottom.viewmodel.HotelViewModel


@Composable
fun AppNavigation(navController: NavHostController, viewModelBooking: BookingViewModel, viewModelHotel: HotelViewModel){

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
            CatalogScreen(navController, viewModelHotel)
        }
        composable(Screen.Bookings.route) {
            BookingsScreen(navController = navController,
                bookingViewModel = viewModelBooking,
                hotelViewModel = viewModelHotel)
        }
        composable(Screen.BookingsInfo.route) {
            BookingsInfoScreen(
                onBack = {navController.navigateUp()}
            )
        }
        composable(Screen.TourInfo.route,
            arguments = listOf(navArgument("hotelId") {type = NavType.IntType})
        ) { backStackEntry ->
            val hotelId = backStackEntry.arguments?.getInt("hotelId") ?: 1
            TourInfoScreen(
                onBack = {navController.navigateUp()},
                bookingviewModel = viewModelBooking,
                hotelViewModel = viewModelHotel,
                hotelId = hotelId
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



