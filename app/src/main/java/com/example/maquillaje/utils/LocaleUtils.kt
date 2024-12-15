package com.example.maquillaje.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.Locale

object LocaleUtils {
    fun createLocaleContext(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(LocaleList(locale))
            context.createConfigurationContext(config)
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
            val newContext = context.createConfigurationContext(config)
            // Para versiones anteriores a N, también actualizamos la configuración
            @Suppress("DEPRECATION")
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            newContext
        }
    }

    fun setLocale(context: Context, languageCode: String): Boolean {
        try {
            val currentLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales[0]
            } else {
                @Suppress("DEPRECATION")
                context.resources.configuration.locale
            }
            
            val newLocale = Locale(languageCode)
            
            if (currentLocale.language == newLocale.language) {
                return false
            }

            Locale.setDefault(newLocale)
            val config = Configuration(context.resources.configuration)
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocales(LocaleList(newLocale))
            } else {
                @Suppress("DEPRECATION")
                config.locale = newLocale
            }
            
            context.createConfigurationContext(config)
            
            // Para versiones anteriores a N, también actualizamos la configuración
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                @Suppress("DEPRECATION")
                context.resources.updateConfiguration(config, context.resources.displayMetrics)
            }
            
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}