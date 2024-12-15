package com.example.maquillaje

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.maquillaje.navigation.Screen
import com.example.maquillaje.screens.*
import com.example.maquillaje.ui.theme.MaquillajeTheme
import com.example.maquillaje.utils.AnimationUtils
import com.example.maquillaje.utils.LocaleUtils
import com.example.maquillaje.viewmodel.LanguageViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val languageViewModel: LanguageViewModel by viewModels()

    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences("maquillaje_prefs", Context.MODE_PRIVATE)
        val languageCode = prefs.getString("language_code", "en") ?: "en"
        val context = LocaleUtils.createLocaleContext(newBase, languageCode)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                languageViewModel.shouldRecreateActivity.collect { shouldRecreate ->
                    if (shouldRecreate) {
                        languageViewModel.onActivityRecreated()
                        recreate()
                    }
                }
            }
        }

        setContent {
            MaquillajeApp(languageViewModel)
        }
    }
}

@Composable
fun MaquillajeApp(languageViewModel: LanguageViewModel) {
    val currentLanguage by languageViewModel.currentLanguage.collectAsState()
    
    MaquillajeTheme {
        AnimatedContent(
            targetState = currentLanguage,
            transitionSpec = {
                ContentTransform(
                    targetContentEnter = AnimationUtils.enterTransition,
                    initialContentExit = AnimationUtils.exitTransition
                )
            },
            modifier = Modifier.fillMaxSize(),
            label = "language_transition"
        ) { _ ->
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
                        SettingsScreen(navController, languageViewModel)
                    }
                    composable(Screen.About.route) {
                        AboutScreen(navController)
                    }
                }
            }
        }
    }
}