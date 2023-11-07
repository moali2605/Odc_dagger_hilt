package com.example.androidd07.model.Dto



data class CallDto(
    var products: List<ProductDto>,
    var total: Int,
    var skip: Int,
    var limit: Int
)
