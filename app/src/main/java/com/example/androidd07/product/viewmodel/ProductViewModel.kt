package com.example.androidd07.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidd07.model.Dto.ProductDto
import com.example.androidd07.model.Repo.ApiState
import com.example.androidd07.model.Repo.RepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProductViewModel(val repository: RepositoryInterface) : ViewModel() {

    val liveProduct: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Loading)

    init {
        getAllProduct()
    }

    fun getAllProduct() {
        viewModelScope.launch {
            repository.getAllProduct()
                .catch { liveProduct.value = ApiState.Failure(it) }
                .collect {
                    liveProduct.value = ApiState.Success(it)
                }
        }
    }

    fun addToFav(productDto: ProductDto) {
        viewModelScope.launch {
            repository.insertProduct(productDto)
        }
    }

}