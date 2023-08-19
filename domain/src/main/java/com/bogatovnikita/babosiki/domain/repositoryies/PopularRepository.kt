package com.bogatovnikita.babosiki.domain.repositoryies

import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import kotlinx.coroutines.flow.StateFlow

interface PopularRepository {
    fun getAllCurrencies(): StateFlow<ExchangeRate>
}