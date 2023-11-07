package com.example.androidd07.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidd07.model.Dto.ProductDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product")
    fun getAllProduct(): Flow<List<ProductDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(data: ProductDto)

    @Delete
    suspend fun deleteProduct(data: ProductDto)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllProduct(data:List<ProductDto>)
}