package com.bogatovnikita.babosiki.domain.models

data class ExchangeRate(
    val baseCurrency: String,
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean,
    val timestamp: Long
)