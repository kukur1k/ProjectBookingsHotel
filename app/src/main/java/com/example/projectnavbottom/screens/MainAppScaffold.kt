package com.example.projectnavbottom.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.projectnavbottom.navigation.Screen

@Composable
fun MainAppScaffold(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val shouldShowBottomNav = when {
        currentRoute?.startsWith("home") == true -> true
        currentRoute?.startsWith(Screen.Profile.route) == true -> true
        currentRoute?.startsWith(Screen.Catalog.route) == true -> true
        currentRoute?.startsWith(Screen.Bookings.route) == true -> true
        else -> {
            false
        }
    }


}