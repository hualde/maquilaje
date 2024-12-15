package com.example.maquillaje

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.maquillaje.data.PreferencesManager
import com.example.maquillaje.utils.LocaleUtils

class MaquillajeApplication : Application() {
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate() {
        super.onCreate()
        preferencesManager = PreferencesManager(this)
    }

    override fun attachBaseContext(base: Context) {
        // Usamos directamente SharedPreferences para evitar problemas con DataStore
        val prefs = base.getSharedPreferences("maquillaje_prefs", Context.MODE_PRIVATE)
        val languageCode = prefs.getString("language_code", "en") ?: "en"
        super.attachBaseContext(LocaleUtils.createLocaleContext(base, languageCode))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val languageCode = preferencesManager.getStoredLanguageCode()
        val context = LocaleUtils.createLocaleContext(this, languageCode)
        resources.updateConfiguration(context.resources.configuration, context.resources.displayMetrics)
    }
}
