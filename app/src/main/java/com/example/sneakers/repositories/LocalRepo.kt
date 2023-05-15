package com.example.sneakers.repositories

import com.example.sneakers.db.dao.SneakerDao
import com.example.sneakers.models.Sneaker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepo @Inject constructor(
    private val sneakersLocalData: SneakerDao
    ) : ILocalRepo {
    override fun getAllSneakers(): Flow<List<Sneaker>> {
       return sneakersLocalData.getAllSneakers()
    }

    override suspend fun addDummyData() {
        sneakersLocalData.addDummyData()
    }

    override suspend fun insertSneaker(sneaker: Sneaker) {
        sneakersLocalData.insertSneaker(sneaker)
    }

    override suspend fun addSneakerToCart(sneakerId: Int) {
        sneakersLocalData.addSneakerToCart(sneakerId)
    }

    override suspend fun removeSneakerFromCart(sneakerId: Int) {
        sneakersLocalData.removeSneakerFromCart(sneakerId)
    }

    override fun getAllCartAddedSneakers(): Flow<List<Sneaker>> {
        return sneakersLocalData.getAllCartAddedSneakers()
    }

}