package com.example.androidd07.Favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidd07.model.Repo.RepositoryInterface
import java.lang.IllegalArgumentException

class FavViewModelFactory (private val repository: RepositoryInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(FavViewModel::class.java)){
            FavViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Not Found")
        }
    }
}