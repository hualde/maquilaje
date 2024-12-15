package com.example.maquillaje.data

data class Language(
    val code: String,
    val name: String,
    val flag: String
) {
    companion object {
        val supportedLanguages = listOf(
            Language("es", "Español", "🇪🇸"),
            Language("en", "English", "🇺🇸"),
            Language("fr", "Français", "🇫🇷"),
            Language("pt", "Português", "🇵🇹")
        )
    }
} 