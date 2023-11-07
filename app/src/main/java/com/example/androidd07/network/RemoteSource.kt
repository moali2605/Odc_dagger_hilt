package com.example.androidd07.network

import com.example.androidd07.model.Dto.ProductDto
import kotlinx.coroutines.flow.Flow


interface RemoteSource {

   suspend fun getProductFromApi(): Flow<List<ProductDto>>

}