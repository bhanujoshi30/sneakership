package com.example.sneakers.repositories

import com.example.sneakers.models.SneakersList
import retrofit2.Response

interface IRemoteRepo {
    suspend fun getSneakersList(): Response<SneakersList>
}