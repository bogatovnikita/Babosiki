package com.bogatovnikita.babosiki.data.mappers

import com.bogatovnikita.babosiki.data.room.ExchangeRatesEntity
import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import javax.inject.Inject

class ExchangeRateToExchangeRatesEntity @Inject constructor() :
        (ExchangeRate) -> ExchangeRatesEntity {

    override fun invoke(exchangeRatesResponse: ExchangeRate): ExchangeRatesEntity {
        return ExchangeRatesEntity(
            baseCurrency = exchangeRatesResponse.baseCurrency,
            rates = exchangeRatesResponse.rates,
            timestamp = exchangeRatesResponse.timestamp
        )
    }
}