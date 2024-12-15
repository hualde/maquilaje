package com.example.maquillaje.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesManager(private val context: Context) {
    
    companion object {
        private val LANGUAGE_CODE = stringPreferencesKey("language_code")
        private const val DEFAULT_LANGUAGE = "en"
    }

    // Obtener el idioma guardado
    val languageCode: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LANGUAGE_CODE] ?: DEFAULT_LANGUAGE
        }

    // Guardar el idioma seleccionado
    suspend fun saveLanguageCode(languageCode: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_CODE] = languageCode
        }
    }
} 