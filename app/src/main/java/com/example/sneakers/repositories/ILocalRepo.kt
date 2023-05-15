package com.example.sneakers.repositories

import com.example.sneakers.models.Sneaker
import kotlinx.coroutines.flow.Flow

interface ILocalRepo {
    fun getAllSneakers(): Flow<List<Sneaker>>
    suspend fun addDummyData()
    suspend fun insertSneaker(sneaker: Sneaker)
    suspend fun addSneakerToCart(sneakerId: Int)
    suspend fun removeSneakerFromCart(sneakerId: Int)
    fun getAllCartAddedSneakers(): Flow<List<Sneaker>>
}