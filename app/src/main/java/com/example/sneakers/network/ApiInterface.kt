package com.example.sneakers.network

import com.example.sneakers.models.SneakersList
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("dummy_URL")
    suspend fun getSneakersList(): Response<SneakersList>
}