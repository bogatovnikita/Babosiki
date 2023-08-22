package com.bogatovnikita.babosiki.data.di

import android.content.Context
import androidx.room.Room
import com.bogatovnikita.babosiki.data.room.ExchangeRatesDao
import com.bogatovnikita.babosiki.data.room.ExchangeRatesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RoomModule {

    @Provides
    @Singleton
    fun provideExchangeRatesDatabase(@ApplicationContext context: Context): ExchangeRatesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ExchangeRatesDatabase::class.java,
            "exchange_rates_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExchangeRatesDao(database: ExchangeRatesDatabase): ExchangeRatesDao {
        return database.exchangeRatesDao()
    }
}