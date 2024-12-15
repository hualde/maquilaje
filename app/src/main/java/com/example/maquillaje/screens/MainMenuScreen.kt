package com.example.maquillaje.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.maquillaje.navigation.Screen

@Composable
fun MainMenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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