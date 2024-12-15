package com.example.maquillaje.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.maquillaje.navigation.Screen
import com.example.maquillaje.viewmodel.LanguageViewModel
import com.example.maquillaje.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    navController: NavController,
    viewModel: LanguageViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.main_title)) }
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
                text = stringResource(R.string.main_title),
                style = MaterialTheme.typography.headlineLarge
            )
            
            Text(
                text = stringResource(R.string.main_description),
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
                Text(stringResource(R.string.btn_take_photo))
            }
            
            Button(
                onClick = { 
                    navController.navigate(Screen.LoadPhoto.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                Text(stringResource(R.string.btn_load_photo))
            }
            
            Button(
                onClick = { 
                    navController.navigate(Screen.Settings.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                Text(stringResource(R.string.btn_settings))
            }
            
            Button(
                onClick = { 
                    navController.navigate(Screen.About.route) {
                        launchSingleTop = true
                    }
                }
            ) {
                Text(stringResource(R.string.btn_about))
            }
        }
    }
}