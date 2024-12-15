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
        private const val PREFS_NAME = "maquillaje_prefs"
        private const val KEY_LANGUAGE = "language_code"
    }

    // Obtener el idioma guardado de manera asíncrona
    val languageCode: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LANGUAGE_CODE] ?: getStoredLanguageCode()
        }

    // Obtener el idioma guardado de manera síncrona
    fun getStoredLanguageCode(): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE
    }

    // Guardar el idioma seleccionado
    suspend fun saveLanguageCode(languageCode: String) {
        // Guardar en DataStore
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_CODE] = languageCode
        }
        
        // Guardar también en SharedPreferences para acceso síncrono
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_LANGUAGE, languageCode)
            .apply()
    }
}