package com.bogatovnikita.babosiki.models

data class MainState(
    val nameCurrency: String = "",
    val loading: Boolean = true,
    val error: Boolean = false,
    val currencyList: List<CurrencyItem> = mutableListOf(),
)
