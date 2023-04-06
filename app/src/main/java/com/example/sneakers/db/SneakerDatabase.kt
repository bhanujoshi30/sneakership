package com.example.sneakers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sneakers.db.dao.SneakerDao
import com.example.sneakers.models.Converters
import com.example.sneakers.models.Sneaker

@Database(entities = [Sneaker::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SneakerDatabase :RoomDatabase() {

    abstract fun sneakerDao(): SneakerDao

    companion object {
        const val DATABASE_NAME = "sneaker_ship_db"
    }
}