package com.bogatovnikita.babosiki.prefs

import android.content.Context
import com.bogatovnikita.babosiki.sort.SortCriteria
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

const val APP_SAVE_SETTINGS = "APP_SAVE_SETTINGS"
const val LAST_CURRENCY = "LAST_CURRENCY"
const val SORT_VALUE = "SORT_VALUE"

@Singleton
class SharedPreferenceProvider @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(APP_SAVE_SETTINGS, Context.MODE_PRIVATE)

    fun saveLastCurrency(currency: String) =
        sharedPreferences.edit().putString(LAST_CURRENCY, currency).apply()

    fun getLastCurrency() = sharedPreferences.getString(LAST_CURRENCY, "RUB")

    fun saveSortValue(value: SortCriteria) =
        sharedPreferences.edit().putString(SORT_VALUE, value.name).apply()

    fun getSortValue() =
        sharedPreferences.getString(SORT_VALUE, SortCriteria.ASCENDING_BY_NAME.name)

}