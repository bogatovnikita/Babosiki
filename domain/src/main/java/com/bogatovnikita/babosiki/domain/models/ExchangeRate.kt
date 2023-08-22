package com.bogatovnikita.babosiki.domain.models

data class ExchangeRate(
    val baseCurrency: String = "",
    val rates: Map<String, Double> = mutableMapOf(),
    val timestamp: Long = 0L
)