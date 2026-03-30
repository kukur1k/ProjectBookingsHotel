package com.example.projectnavbottom.navigation

import android.icu.text.CaseMap

sealed class ScreenType{
    object WithoutBottomNav: ScreenType()
    object WithBottomNav: ScreenType()

}

sealed class Screen(val route: String, val ScreenType: ScreenType){

    object Home: Screen(route = "home", ScreenType.WithBottomNav)
    object Catalog: Screen(route = "catalog", ScreenType.WithBottomNav)
    object Bookings: Screen(route = "bookings", ScreenType.WithBottomNav)
    object Profile: Screen(route = "profile", ScreenType.WithBottomNav)

    object Splash: Screen("splash", ScreenType.WithoutBottomNav)
    object BookingsInfo: Screen(route = "bookings_info", ScreenType.WithoutBottomNav)
    object TourInfo: Screen(route = "tour_info/{hotelId}", ScreenType.WithoutBottomNav){
        fun passId(hotelId: Int): String {
            return "tourInfo/$hotelId"
        }
    }

    object Register: Screen(route = "register", ScreenType.WithoutBottomNav)

    object Login: Screen(route = "login", ScreenType.WithoutBottomNav)

}

sealed class NavItem(val title: String, val route: String){
    object Home: NavItem("Главная", Screen.Home.route)
    object Catalog: NavItem("Каталог", Screen.Catalog.route)
    object Bookings: NavItem("Бронирования", Screen.Bookings.route)
    object Profile: NavItem("Профиль", Screen.Profile.route)
}