package com.bogatovnikita.babosiki.data.repository

import com.bogatovnikita.babosiki.data.mappers.ExchangeRatesResponseToExchangeRateMapper
import com.bogatovnikita.babosiki.data.retrofit.ApiService
import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import com.bogatovnikita.babosiki.domain.repositoryies.CurrencyServiceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyServiceRepositoryImplementation @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ExchangeRatesResponseToExchangeRateMapper
) : CurrencyServiceRepository {

    override suspend fun getAllCurrencies(baseCurrency: String): ExchangeRate {
        val response = apiService.getExchangeRates(baseCurrency)
        return mapper(response, baseCurrency)
    }
}