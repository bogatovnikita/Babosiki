package com.bogatovnikita.babosiki.data.repository

import com.bogatovnikita.babosiki.data.mappers.ExchangeRateToExchangeRatesEntity
import com.bogatovnikita.babosiki.data.mappers.ExchangeRatesEntityToExchangeRate
import com.bogatovnikita.babosiki.data.room.ExchangeRatesDao
import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import com.bogatovnikita.babosiki.domain.repositoryies.CurrencyDBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyDBRepositoryImplementation @Inject constructor(
    private val exchangeRatesDao: ExchangeRatesDao,
    private val mapper: ExchangeRateToExchangeRatesEntity,
    private val mapperToDomain: ExchangeRatesEntityToExchangeRate
) : CurrencyDBRepository {

    override fun getExchangeRate(): Flow<ExchangeRate> {
        return exchangeRatesDao.getLatestExchangeRatesFlow().map {
            mapperToDomain(it)
        }
    }

    override suspend fun updateExchangeRate(input: ExchangeRate) {
        exchangeRatesDao.insertExchangeRates(mapper(input))
    }
}
