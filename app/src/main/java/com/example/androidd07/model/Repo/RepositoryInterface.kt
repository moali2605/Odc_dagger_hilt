package com.example.androidd07.model.Repo

import com.example.androidd07.model.Dto.CallDto
import com.example.androidd07.model.Dto.ProductDto
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {
        suspend fun getAllProduct(): Flow<List<ProductDto>>
        suspend fun insertProduct(productDto: ProductDto)
        suspend fun deleteProduct(productDto: ProductDto)
         fun getStoredProduct(): Flow<List<ProductDto>>
    }
