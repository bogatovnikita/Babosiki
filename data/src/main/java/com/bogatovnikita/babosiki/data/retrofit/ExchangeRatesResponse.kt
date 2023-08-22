package com.bogatovnikita.babosiki.data.retrofit

 data class ExchangeRatesResponse(
    val rates: Map<String, Double>,
    val timestamp: Long
)