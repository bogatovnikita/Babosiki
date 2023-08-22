package com.bogatovnikita.babosiki.data.di

import com.bogatovnikita.babosiki.data.repository.CurrencyDBRepositoryImplementation
import com.bogatovnikita.babosiki.data.repository.CurrencyServiceRepositoryImplementation
import com.bogatovnikita.babosiki.domain.repositoryies.CurrencyDBRepository
import com.bogatovnikita.babosiki.domain.repositoryies.CurrencyServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideCurrencyDBRepository(repository: CurrencyDBRepositoryImplementation): CurrencyDBRepository {
        return repository
    }

    @Provides
    fun provideCurrencyServiceRepository(repository: CurrencyServiceRepositoryImplementation): CurrencyServiceRepository {
        return repository
    }

}