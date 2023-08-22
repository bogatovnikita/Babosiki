package com.bogatovnikita.babosiki.domain.repositoryies

import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface CurrencyDBRepository {

    fun getExchangeRate(): Flow<ExchangeRate>

    suspend fun updateExchangeRate(input:ExchangeRate)
}