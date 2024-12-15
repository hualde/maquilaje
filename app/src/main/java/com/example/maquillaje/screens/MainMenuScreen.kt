package com.example.maquillaje.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.maquillaje.navigation.Screen
import com.example.maquillaje.data.Language
import com.example.maquillaje.viewmodel.LanguageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    navController: NavController,
    viewModel: LanguageViewModel
) {
    var showLanguageMenu by remember { mutableStateOf(false) }
    val currentLanguage by viewModel.currentLanguage.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ladybug") },
                actions = {
                    Box {
                        IconButton(onClick = { showLanguageMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Seleccionar idioma"
                            )
                        }
                        
                        DropdownMenu(
                            expanded = showLanguageMenu,
                            onDismissRequest = { showLanguageMenu = false }
                        ) {
                            Language.supportedLanguages.forEach { language ->
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(language.flag)
                                            Text(language.name)
                                        }
                                    },
                                    onClick = {
                                        viewModel.setLanguage(language)
                                        showLanguageMenu = false
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Ladybug",
                style = MaterialTheme.typography.headlineLarge
            )
            
            Text(
                text = "Toma una foto y descubre más sobre tu maquillaje.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = { 
                    navController.navigate(Screen.TakePhoto.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                Text("Tomar Foto")
            }
            
            Button(
                onClick = { 
                    navController.navigate(Screen.LoadPhoto.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                Text("Cargar Foto")
            }
            
            Button(
                onClick = { 
                    navController.navigate(Screen.Settings.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                Text("Configuración")
            }
            
            Button(
                onClick = { 
                    navController.navigate(Screen.About.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                Text("Acerca De")
            }
        }
    }
} 