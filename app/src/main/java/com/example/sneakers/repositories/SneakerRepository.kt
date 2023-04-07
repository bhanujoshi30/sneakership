package com.example.sneakers.repositories

import com.example.sneakers.db.dao.SneakerDao
import com.example.sneakers.models.Sneaker
import com.example.sneakers.models.SneakersList
import com.example.sneakers.network.ApiInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class SneakerRepository @Inject constructor(
    private val mApi: ApiInterface,
    private val dispatcher: CoroutineDispatcher,
    private val sneakersDao: SneakerDao
) : ISneakerRepository {
    override suspend fun getSneakers(): Flow<List<Sneaker>> {

        val sneakersFromDb = sneakersDao.getAllSneakers().firstOrNull()
        var sneakersFromApi: SneakersList?
        return if (sneakersFromDb != null && sneakersFromDb.isNotEmpty()) {
            flowOf(sneakersFromDb).take(100)
        } else {
            sneakersDao.addDummyData() //dummy data
            /*
            * Network call commented
            * */
//            val emptySneakersList = SneakersList()
//            withContext(dispatcher) {
//                sneakersFromApi = mApi.getSneakersList().body() ?: emptySneakersList
//            }
//            if (sneakersFromApi is List<*>) {
//                saveSneakers(sneakersFromApi as List<Sneaker>)
//            }
            sneakersDao.getAllSneakers()
        }
    }

    override suspend fun saveSneakers(sneakers: List<Sneaker>) {
        for (sneaker in sneakers)
            sneakersDao.insertSneaker(sneaker)
    }

    override suspend fun addSneakerToCart(sneakerId: Int) {
        sneakersDao.addSneakerToCart(sneakerId)
    }

    override suspend fun removeSneakerFromCart(sneakerId: Int) {
        sneakersDao.removeSneakerFromCart(sneakerId)
    }

    override suspend fun getCartAddedSneakers(): Flow<List<Sneaker>> {
        return sneakersDao.getAllCartAddedSneakers()
    }

}

