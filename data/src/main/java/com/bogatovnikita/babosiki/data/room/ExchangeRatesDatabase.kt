package com.bogatovnikita.babosiki.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ExchangeRatesEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ExchangeRatesDatabase : RoomDatabase() {
    abstract fun exchangeRatesDao(): ExchangeRatesDao
}
