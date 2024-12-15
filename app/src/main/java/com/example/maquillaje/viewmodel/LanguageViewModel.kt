package com.example.maquillaje.viewmodel

import androidx.lifecycle.ViewModel
import com.example.maquillaje.data.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LanguageViewModel : ViewModel() {
    private val _currentLanguage = MutableStateFlow(Language.supportedLanguages[0])
    val currentLanguage: StateFlow<Language> = _currentLanguage.asStateFlow()

    fun setLanguage(language: Language) {
        _currentLanguage.value = language
        // TODO: Implementar la persistencia del idioma seleccionado
    }
} 