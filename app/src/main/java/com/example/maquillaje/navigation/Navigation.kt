package com.example.maquillaje.navigation

sealed class Screen(val route: String) {
    object MainMenu : Screen("main_menu")
    object TakePhoto : Screen("take_photo")
    object LoadPhoto : Screen("load_photo")
    object Settings : Screen("settings")
    object About : Screen("about")
} 