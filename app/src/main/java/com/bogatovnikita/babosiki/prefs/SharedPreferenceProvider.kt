package com.bogatovnikita.babosiki.prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

const val APP_SAVE_SETTINGS = "APP_SAVE_SETTINGS"
const val LAST_CURRENCY = "LAST_CURRENCY"

@Singleton
class SharedPreferenceProvider @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(APP_SAVE_SETTINGS, Context.MODE_PRIVATE)

    fun saveLastCurrency(currency: String) =
        sharedPreferences.edit().putString(LAST_CURRENCY, currency).apply()

    fun getLastCurrency() = sharedPreferences.getString(LAST_CURRENCY, "RUB")
}