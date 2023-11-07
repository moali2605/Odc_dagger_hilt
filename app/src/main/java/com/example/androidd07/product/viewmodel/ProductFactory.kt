package com.example.androidd07.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidd07.model.Repo.RepositoryInterface
import java.lang.IllegalArgumentException

class ProductFactory(private val repository:RepositoryInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ProductViewModel::class.java)){
            ProductViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Not Found")
        }
    }
}