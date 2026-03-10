package com.example.projectnavbottom.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainAppScaffold(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route


    val screenType = when {
        currentRoute?.startsWith("home") == true -> true
        currentRoute?.startsWith(Screen.Catalog.route) == true -> true
        currentRoute?.startsWith(Screen.Bookings.route) == true -> true
        currentRoute?.startsWith(Screen.Profile.route) == true -> false
        else -> {
            false
        }
    }

    var selectedItem by remember { mutableStateOf<NavItem>(NavItem.Home) }

    LaunchedEffect(currentRoute) {
        selectedItem = when {
            currentRoute?.startsWith("home") == true -> NavItem.Home
            currentRoute?.startsWith(Screen.Catalog.route) == true -> NavItem.Catalog
            currentRoute?.startsWith(Screen.Bookings.route) == true -> NavItem.Bookings
            currentRoute?.startsWith(Screen.Profile.route) == true -> NavItem.Profile
            else -> {
                selectedItem
            }
        }

    }

    Scaffold(
        bottomBar = {
            if(screenType) {
                BottomNavigationPanel(
                    selectedItem = selectedItem,
                    onItemSelected = {
                        selectedItem = it
                        when (it) {
                            NavItem.Home -> {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Home.route) {
                                        inclusive = true
                                    }
                                }
                            }

                            NavItem.Bookings -> {
                                navController.navigate(Screen.Bookings.route) {
                                    popUpTo(Screen.Bookings.route) {
                                        inclusive = true
                                    }
                                }
                            }

                            NavItem.Catalog -> {
                                navController.navigate(Screen.Catalog.route) {
                                    popUpTo(Screen.Catalog.route) {
                                        inclusive = true
                                    }
                                }
                            }

                            NavItem.Profile -> {
                                navController.navigate(Screen.Profile.route) {
                                    popUpTo(Screen.Profile.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }

                )
            }
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            AppNavigation(navController)
        }
    }
}