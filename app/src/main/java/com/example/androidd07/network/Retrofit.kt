package com.example.androidd07.network

import com.example.androidd07.model.Dto.ProductDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper(val retrofit: ApiInterFace) :RemoteSource{


    override suspend fun getProductFromApi(): Flow<List<ProductDto>> {
        val products = retrofit.getProduct().products
        return flowOf(products)
    }
}
