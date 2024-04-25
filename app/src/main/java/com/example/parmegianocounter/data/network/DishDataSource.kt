package com.example.parmegianocounter.data.network

import com.example.parmegianocounter.data.model.DishData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface DishDataSource {

    @Headers(
        "Content-Type: application/json"
    )
    @GET("/menu")
    suspend fun getJokes(): Response<DishData>
}