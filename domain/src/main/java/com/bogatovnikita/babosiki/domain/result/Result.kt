package com.bogatovnikita.babosiki.domain.result

import com.bogatovnikita.babosiki.domain.models.ExchangeRate

sealed class Result {
    data class Success(val data: ExchangeRate) : Result()
    data class Error(val message: Throwable) : Result()
}