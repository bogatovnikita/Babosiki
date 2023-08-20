package com.bogatovnikita.babosiki.domain.repositoryies

import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface PopularRepository {
    suspend fun getAllCurrencies(baseCurrency: String): Flow<ExchangeRate>
}