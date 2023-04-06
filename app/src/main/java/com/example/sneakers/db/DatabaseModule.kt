package com.example.sneakers.db

import android.content.Context
import androidx.room.Room
import com.example.sneakers.app.MainApp
import com.example.sneakers.db.dao.SneakerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    @Synchronized fun getInstance(): SneakerDatabase {
        return provideSneakerShipDatabase(MainApp.appContext)
    }

    private fun provideSneakerShipDatabase(@ApplicationContext context: Context): SneakerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SneakerDatabase::class.java,
            SneakerDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideSneakerDao(sneakerShipDatabase: SneakerDatabase): SneakerDao {
        return sneakerShipDatabase.sneakerDao()
    }
}
