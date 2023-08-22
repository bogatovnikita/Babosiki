package com.bogatovnikita.babosiki.data.mappers

import com.bogatovnikita.babosiki.data.room.ExchangeRatesEntity
import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import javax.inject.Inject

class ExchangeRatesEntityToExchangeRate @Inject constructor() :
        (ExchangeRatesEntity) -> ExchangeRate {

    override fun invoke(exchangeRatesEntity: ExchangeRatesEntity): ExchangeRate {
        return ExchangeRate(
            rates = exchangeRatesEntity.rates,
            timestamp = exchangeRatesEntity.timestamp,
            baseCurrency = exchangeRatesEntity.baseCurrency
        )
    }
}