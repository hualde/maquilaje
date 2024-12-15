package com.example.maquillaje.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.maquillaje.data.AppLanguage
import com.example.maquillaje.data.PreferencesManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LanguageViewModel(application: Application) : AndroidViewModel(application) {
    private val preferencesManager = PreferencesManager(application)
    
    private val defaultLanguage = AppLanguage.supportedLanguages.find { it.code == "en" }
        ?: AppLanguage.supportedLanguages[0]
    
    private val _currentLanguage = MutableStateFlow(defaultLanguage)
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesManager.languageCode
                .map { code ->
                    AppLanguage.supportedLanguages.find { it.code == code }
                        ?: defaultLanguage
                }
                .collect { language ->
                    _currentLanguage.value = language
                }
        }
    }

    fun setLanguage(language: AppLanguage) {
        viewModelScope.launch {
            preferencesManager.saveLanguageCode(language.code)
            _currentLanguage.value = language
        }
    }
} 