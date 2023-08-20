package com.bogatovnikita.babosiki.data.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRatesRepository @Inject constructor(private val exchangeRatesDao: ExchangeRatesDao) {
    suspend fun insertExchangeRates(exchangeRatesEntity: ExchangeRatesEntity) {
        exchangeRatesDao.insertExchangeRates(exchangeRatesEntity)
    }

    fun getLatestExchangeRatesFlow(): Flow<ExchangeRatesEntity?> {
        return exchangeRatesDao.getLatestExchangeRatesFlow()
    }
}