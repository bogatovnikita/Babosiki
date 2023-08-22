package com.bogatovnikita.babosiki.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

 interface ApiService {
    @GET("exchangerates_data/latest")
    suspend fun getExchangeRates(
        @Query("base") baseCurrency: String
    ): ExchangeRatesResponse
}