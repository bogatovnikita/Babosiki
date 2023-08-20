package com.bogatovnikita.babosiki.view_model

data class MainState(
    val lastUpdate: String = "",
    val nameCurrency: String = "",
    val loading: Boolean = false,
    val error: Boolean = false,
    val currencyList: Map<String, Double> = mutableMapOf()
)
