package com.bogatovnikita.babosiki.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ExchangeRatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRates(exchangeRatesEntity: ExchangeRatesEntity)

    @Query("SELECT * FROM exchange_rates ORDER BY timestamp DESC LIMIT 1")
    fun getLatestExchangeRatesFlow(): Flow<ExchangeRatesEntity>
}