package com.bogatovnikita.babosiki.di

import com.bogatovnikita.babosiki.domain.usecase.GetExchangeRateUseCase
import com.bogatovnikita.babosiki.domain.usecase.GetExchangeRateUseCaseImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object DomainModule {
        @Provides
        fun provideGetExchangeRateUseCase(
            getExchangeRateUseCase: GetExchangeRateUseCaseImplementation
        ): GetExchangeRateUseCase = getExchangeRateUseCase
    }

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers
}