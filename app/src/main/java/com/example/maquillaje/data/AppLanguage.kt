package com.example.maquillaje.data

data class AppLanguage(
    val code: String,
    val name: String,
    val flag: String
) {
    companion object {
        val supportedLanguages = listOf(
            AppLanguage("es", "Español", "🇪🇸"),
            AppLanguage("en", "English", "🇺🇸"),
            AppLanguage("fr", "Français", "🇫🇷"),
            AppLanguage("pt", "Português", "🇵🇹")
        )
    }
} 