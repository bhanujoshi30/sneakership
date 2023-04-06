package com.example.sneakers.repositories

import com.example.sneakers.db.dao.SneakerDao
import com.example.sneakers.models.Sneaker
import com.example.sneakers.models.SneakersList
import com.example.sneakers.network.ApiInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SneakerRepositoryImpl @Inject constructor(
    private val mApi: ApiInterface,
    private val dispatcher: CoroutineDispatcher,
    private val sneakersDao: SneakerDao
) : SneakerRepository {
    override suspend fun getSneakers(): Flow<List<Sneaker>> {

        val sneakersFromDb = sneakersDao.getAllSneakers().firstOrNull()
        var sneakersFromApi: SneakersList?
        return if (sneakersFromDb != null && sneakersFromDb.isNotEmpty()) {
            flowOf(sneakersFromDb).filter { sneaker ->
                sneaker.isNotEmpty()
            }.take(100)
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
}

