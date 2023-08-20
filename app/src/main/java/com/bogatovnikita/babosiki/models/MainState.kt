package com.bogatovnikita.babosiki.models

data class MainState(
    val lastUpdate: String = "",
    val nameCurrency: String = "",
    val loading: Boolean = false,
    val error: Boolean = false,
    val currencyList: List<CurrencyItem> = mutableListOf()
)
