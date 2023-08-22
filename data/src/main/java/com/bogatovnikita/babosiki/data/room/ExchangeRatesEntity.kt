package com.bogatovnikita.babosiki.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
 data class ExchangeRatesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val baseCurrency: String,
    @ColumnInfo(name = "rates") val rates: Map<String, Double>,
    val timestamp: Long
)