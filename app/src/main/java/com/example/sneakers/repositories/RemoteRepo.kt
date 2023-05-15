package com.example.sneakers.repositories

import com.example.sneakers.models.SneakersList
import com.example.sneakers.network.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class RemoteRepo @Inject constructor(
    private val mApi: ApiInterface
) : IRemoteRepo{
    override suspend fun getSneakersList(): Response<SneakersList> {
        return mApi.getSneakersList()
    }
}