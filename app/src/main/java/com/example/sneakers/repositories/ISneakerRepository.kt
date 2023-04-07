package com.example.sneakers.repositories

import com.example.sneakers.models.Sneaker
import kotlinx.coroutines.flow.Flow

interface ISneakerRepository {
    suspend fun getSneakers(): Flow<List<Sneaker>>
    suspend fun saveSneakers(sneakers: List<Sneaker>)
    suspend fun addSneakerToCart(sneakerId: Int)
    suspend fun removeSneakerFromCart(sneakerId: Int)
    suspend fun getCartAddedSneakers(): Flow<List<Sneaker>>
}