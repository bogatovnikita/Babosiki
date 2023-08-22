package com.bogatovnikita.babosiki.domain.repositoryies

import com.bogatovnikita.babosiki.domain.models.ExchangeRate

interface CurrencyServiceRepository {

    suspend fun getAllCurrencies(baseCurrency: String): ExchangeRate
}