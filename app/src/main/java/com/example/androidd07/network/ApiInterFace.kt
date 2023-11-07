package com.example.androidd07.network

import com.example.androidd07.model.Dto.CallDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiInterFace {
    @GET("products")
    suspend fun getProduct(): CallDto
}
