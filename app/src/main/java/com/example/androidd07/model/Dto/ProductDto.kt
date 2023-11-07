package com.example.androidd07.model.Dto

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "product")
class ProductDto(
    val id: Int,
    @PrimaryKey
    @NonNull
    val title: String,
    val description: String,
    val price: Double,
    val rating: Double,
    val brand: String,
    val thumbnail: String
):Serializable
