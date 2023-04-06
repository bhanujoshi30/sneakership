package com.example.sneakers.repositories

import com.example.sneakers.models.Sneaker
import kotlinx.coroutines.flow.Flow

interface SneakerRepository {
    suspend fun getSneakers(): Flow<List<Sneaker>>
    suspend fun saveSneakers(sneakers: List<Sneaker>)
}