package com.example.androidd07.db

import com.example.androidd07.model.Dto.ProductDto
import kotlinx.coroutines.flow.Flow

interface LocalSource {
   suspend fun insertProduct(productDto: ProductDto)
   suspend fun deleteProduct(productDto: ProductDto)
    fun getProduct(): Flow<List<ProductDto>>
}