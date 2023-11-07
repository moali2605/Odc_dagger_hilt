package com.example.androidd07.model.Repo

import com.example.androidd07.db.LocalSource
import com.example.androidd07.model.Dto.CallDto
import com.example.androidd07.model.Dto.ProductDto
import com.example.androidd07.network.RemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

class Repository private constructor(val localSource: LocalSource,val remoteSource: RemoteSource):RepositoryInterface {
    companion object {
        private var repository:Repository?=null
        fun getInstance(localSource: LocalSource, remoteSource: RemoteSource): Repository {
            return repository ?: synchronized(this) {
                repository ?: Repository(localSource, remoteSource).also {
                    repository = it
                }
            }
        }
    }
    override suspend fun getAllProduct(): Flow<List<ProductDto>> {
       return remoteSource.getProductFromApi()
    }

    override suspend fun insertProduct(productDto: ProductDto) {
        localSource.insertProduct(productDto)
    }

    override suspend fun deleteProduct(productDto: ProductDto) {
        localSource.deleteProduct(productDto)
    }

    override  fun getStoredProduct(): Flow<List<ProductDto>> {
        return localSource.getProduct()
    }
}