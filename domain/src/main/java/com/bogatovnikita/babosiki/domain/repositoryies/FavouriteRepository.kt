package com.bogatovnikita.babosiki.domain.repositoryies

import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import kotlinx.coroutines.flow.StateFlow

interface FavouriteRepository {
    fun getAllFavourites(): StateFlow<ExchangeRate>
}