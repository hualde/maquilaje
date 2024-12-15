package com.example.maquillaje.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.maquillaje.data.AppLanguage
import com.example.maquillaje.data.PreferencesManager
import com.example.maquillaje.utils.LocaleUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LanguageViewModel(application: Application) : AndroidViewModel(application) {
    private val preferencesManager = PreferencesManager(application)
    private val context = application
    
    private val defaultLanguage = AppLanguage.supportedLanguages.find { it.code == "en" }
        ?: AppLanguage.supportedLanguages[0]
    
    private val _currentLanguage = MutableStateFlow(defaultLanguage)
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private val _shouldRecreateActivity = MutableStateFlow(false)
    val shouldRecreateActivity: StateFlow<Boolean> = _shouldRecreateActivity.asStateFlow()

    init {
        // Cargar el idioma guardado
        viewModelScope.launch {
            preferencesManager.languageCode
                .map { code ->
                    AppLanguage.supportedLanguages.find { it.code == code }
                        ?: defaultLanguage
                }
                .collect { language ->
                    _currentLanguage.value = language
                    LocaleUtils.setLocale(context, language.code)
                }
        }
    }

    fun setLanguage(language: AppLanguage) {
        viewModelScope.launch {
            if (language.code != currentLanguage.value.code) {
                // Guardar el nuevo idioma
                preferencesManager.saveLanguageCode(language.code)
                
                // Aplicar el cambio de idioma
                if (LocaleUtils.setLocale(context, language.code)) {
                    _currentLanguage.value = language
                }
            }
        }
    }

    fun updateLanguage(languageCode: String) {
        viewModelScope.launch {
            preferencesManager.saveLanguageCode(languageCode)
            if (LocaleUtils.setLocale(context, languageCode)) {
                // Actualizar el estado del idioma actual
                _currentLanguage.value = AppLanguage.supportedLanguages.find { it.code == languageCode }
                    ?: defaultLanguage
                
                // Notificar que se debe recrear la actividad
                _shouldRecreateActivity.value = true
            }
        }
    }

    fun onActivityRecreated() {
        _shouldRecreateActivity.value = false
    }
}