package com.example.maquillaje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.maquillaje.navigation.Screen
import com.example.maquillaje.screens.MainMenuScreen
import com.example.maquillaje.screens.AboutScreen
import com.example.maquillaje.screens.SettingsScreen
import com.example.maquillaje.screens.TakePhotoScreen
import com.example.maquillaje.screens.LoadPhotoScreen
import com.example.maquillaje.ui.theme.MaquillajeTheme
import com.example.maquillaje.viewmodel.LanguageViewModel

class MainActivity : ComponentActivity() {
    private val languageViewModel: LanguageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaquillajeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainMenu.route
                    ) {
                        composable(Screen.MainMenu.route) {
                            MainMenuScreen(navController, languageViewModel)
                        }
                        composable(Screen.TakePhoto.route) {
                            TakePhotoScreen(navController)
                        }
                        composable(Screen.LoadPhoto.route) {
                            LoadPhotoScreen(navController)
                        }
                        composable(Screen.Settings.route) {
                            SettingsScreen(navController)
                        }
                        composable(Screen.About.route) {
                            AboutScreen(navController)
                        }
                    }
                }
            }
        }
    }
}