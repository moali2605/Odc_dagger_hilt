package com.example.androidd07.db

import android.content.Context
import com.example.androidd07.model.Dto.ProductDto
import com.example.androidd07.model.Repo.Repository
import com.example.androidd07.network.RemoteSource
import kotlinx.coroutines.flow.Flow


class ConcreteLocalSource private constructor(context: Context): LocalSource {
    private lateinit var context: Context
    private val database: DataBase by lazy { DataBase.getInstance(context) }
   private val productDAO: ProductDAO by lazy { database.getProductDao() }

    companion object{
        private var concreteLocalSource:ConcreteLocalSource?=null
        fun getInstance(context: Context): ConcreteLocalSource {
            return concreteLocalSource ?: synchronized(this) {
                concreteLocalSource ?: ConcreteLocalSource(context).also {
                    concreteLocalSource = it
                }
            }
        }

    }

    override suspend fun insertProduct(productDto: ProductDto) {
        productDAO.insertProduct(productDto)
    }

    override suspend fun deleteProduct(productDto: ProductDto) {
        productDAO.deleteProduct(productDto)
    }

    override  fun getProduct(): Flow<List<ProductDto>> {
        return productDAO.getAllProduct()
    }
}