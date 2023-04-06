package com.example.sneakers.di

import com.example.sneakers.repositories.SneakerRepository
import com.example.sneakers.repositories.SneakerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SneakerRepoModule {
    @Binds
    @Singleton
    abstract fun providesSneakerRepository(sneakerRepositoryImpl: SneakerRepositoryImpl): SneakerRepository

}