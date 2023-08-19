package com.bogatovnikita.babosiki.domain.usecase

import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import com.bogatovnikita.babosiki.domain.repositoryies.PopularRepository
import kotlinx.coroutines.flow.Flow

typealias DefaultResult<T> = () -> Flow<Result<T>>

class PopularUseCase(private val popularRepository: PopularRepository) :
    DefaultResult<ExchangeRate> {
    override fun invoke(): Flow<Result<ExchangeRate>> {
        TODO("Not yet implemented")
    }

}