package com.bogatovnikita.babosiki.domain.models

data class ExchangeRate(
    val baseCurrency: String = "",
    val date: String = "",
    val rates: Map<String, Double> = mutableMapOf(),
    val success: Boolean = false,
    val timestamp: Long = 0L
)