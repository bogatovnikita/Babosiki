package com.bogatovnikita.babosiki.data.repository

import com.bogatovnikita.babosiki.data.retrofit.ApiService
import com.bogatovnikita.babosiki.domain.models.ExchangeRate
import com.bogatovnikita.babosiki.domain.repositoryies.PopularRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularRepositoryImplementation @Inject constructor(private val apiService: ApiService) :
    PopularRepository {
    override fun getAllCurrencies(): StateFlow<ExchangeRate> {
        TODO("Not yet implemented")
    }
}