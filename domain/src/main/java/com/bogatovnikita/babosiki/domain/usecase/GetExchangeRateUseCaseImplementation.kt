package com.bogatovnikita.babosiki.domain.usecase

import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import com.bogatovnikita.babosiki.domain.repositoryies.CurrencyDBRepository
import com.bogatovnikita.babosiki.domain.repositoryies.CurrencyServiceRepository
import com.bogatovnikita.babosiki.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetExchangeRateUseCase : (Boolean, String) -> Flow<Result>

class GetExchangeRateUseCaseImplementation @Inject constructor(
    private val currencyServiceRepository: CurrencyServiceRepository,
    private val currencyDBRepository: CurrencyDBRepository,
    private val dispatcher: Dispatchers
) : GetExchangeRateUseCase {

    override fun invoke(forceUpdate: Boolean, baseCurrency: String): Flow<Result> {
        return if (forceUpdate) {
            getFromServiceAndUpdateDB(baseCurrency)
        } else {
            currencyDBRepository.getExchangeRate()
        }.map {
            Result.Success(it)
        }.catch {
            Result.Error(it)
        }.flowOn(dispatcher.IO)
    }

    private fun getFromServiceAndUpdateDB(baseCurrency: String): Flow<ExchangeRate> = flow {
        val value = currencyServiceRepository.getAllCurrencies(baseCurrency)
        currencyDBRepository.updateExchangeRate(value)
        emit(value)
    }
}