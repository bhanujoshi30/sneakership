package com.example.sneakers.network

import com.example.sneakers.models.SneakersList
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("icjc-x44x.json")
    suspend fun getSneakersList(): Response<SneakersList>
}