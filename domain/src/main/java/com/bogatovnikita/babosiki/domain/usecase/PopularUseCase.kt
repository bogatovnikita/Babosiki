package com.bogatovnikita.babosiki.domain.usecase

import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import com.bogatovnikita.babosiki.domain.repositoryies.PopularRepository
import com.bogatovnikita.babosiki.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

typealias DefaultResult<T> = (String) -> Flow<Result<T>>

class PopularUseCase @Inject constructor(private val popularRepository: PopularRepository) :
    DefaultResult<ExchangeRate> {

    override fun invoke(baseCurrency: String): Flow<Result<ExchangeRate>> = flow {
        emit(Result.Loading)
        try {
            val exchangeRate = popularRepository.getAllCurrencies(baseCurrency).first()
            emit(Result.Success(exchangeRate))
        } catch (e: Exception) {
            emit(Result.Error("Error loading"))
        }
    }.flowOn(Dispatchers.IO)

}