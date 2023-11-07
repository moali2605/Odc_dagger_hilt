package com.example.androidd07.model.Repo

import com.example.androidd07.model.Dto.ProductDto

sealed class ApiState {

    class Success(val list: List<ProductDto>) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    object Loading : ApiState()

}
