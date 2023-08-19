package com.bogatovnikita.babosiki.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("exchangerates_data/latest")
    fun getExchangeRates(
        @Query("base") baseCurrency: String
    ): Call<ExchangeRatesResponse>
}