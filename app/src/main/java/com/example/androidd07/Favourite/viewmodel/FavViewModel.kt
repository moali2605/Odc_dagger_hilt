package com.example.androidd07.Favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidd07.model.Dto.ProductDto
import com.example.androidd07.model.Repo.RepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavViewModel(val repository: RepositoryInterface) : ViewModel() {

    val stateFavProduct: MutableStateFlow<List<ProductDto>> = MutableStateFlow(emptyList())

    init {
        getStoredData()
    }

    fun getStoredData() {
        viewModelScope.launch {
            repository.getStoredProduct().collect {
                    stateFavProduct.value = it
            }
        }
    }

    fun deleteFav(productDto: ProductDto) {
        viewModelScope.launch {
            repository.deleteProduct(productDto)
//            repository.getStoredProduct().collect {
//                liveFavProduct.postValue(it)
//            }
        }
    }

}