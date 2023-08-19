package com.bogatovnikita.babosiki.data.retrofit

data class ExchangeRatesResponse(
    val baseCurrency: String,
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean,
    val timestamp: Long
)