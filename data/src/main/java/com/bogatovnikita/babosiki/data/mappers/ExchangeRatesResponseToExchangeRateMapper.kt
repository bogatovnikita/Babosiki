package com.bogatovnikita.babosiki.data.mappers

import com.bogatovnikita.babosiki.data.retrofit.ExchangeRatesResponse
import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import javax.inject.Inject

 class ExchangeRatesResponseToExchangeRateMapper @Inject constructor() :
        (ExchangeRatesResponse, String) -> ExchangeRate {

    override fun invoke(
        exchangeRatesResponse: ExchangeRatesResponse,
        baseCurrency: String
    ): ExchangeRate {
        return ExchangeRate(
            rates = exchangeRatesResponse.rates,
            timestamp = exchangeRatesResponse.timestamp,
            baseCurrency = baseCurrency
        )
    }
}