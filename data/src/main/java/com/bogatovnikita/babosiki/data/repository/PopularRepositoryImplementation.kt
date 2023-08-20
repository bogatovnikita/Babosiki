package com.bogatovnikita.babosiki.data.repository

import com.bogatovnikita.babosiki.data.retrofit.ApiService
import com.bogatovnikita.babosiki.data.room.ExchangeRatesDao
import com.bogatovnikita.babosiki.data.room.ExchangeRatesEntity
import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import com.bogatovnikita.babosiki.domain.repositoryies.PopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.awaitResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularRepositoryImplementation @Inject constructor(
    private val apiService: ApiService,
    private val exchangeRatesDao: ExchangeRatesDao
) : PopularRepository {
    override suspend fun getAllCurrencies(baseCurrency: String): Flow<ExchangeRate> {
        val response = apiService.getExchangeRates(baseCurrency).awaitResponse()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            exchangeRatesDao.insertExchangeRates(
                ExchangeRatesEntity(
                    baseCurrency = body.baseCurrency,
                    date = body.date,
                    rates = body.rates,
                    success = body.success,
                    timestamp = body.timestamp
                )
            )
        } else {
            throw IllegalAccessException("Bad response")
        }
        return exchangeRatesDao.getLatestExchangeRatesFlow().map {
            ExchangeRate(
                baseCurrency = it.baseCurrency ?: "",
                date = it.date,
                rates = it.rates,
                success = it.success,
                timestamp = it.timestamp
            )
        }
    }
}