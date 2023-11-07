package com.example.androidd07

import com.example.androidd07.Favourite.viewmodel.FavViewModel
import com.example.androidd07.Favourite.viewmodel.FavViewModelFactory
import com.example.androidd07.db.ConcreteLocalSource
import com.example.androidd07.db.LocalSource
import com.example.androidd07.model.Repo.Repository
import com.example.androidd07.model.Repo.RepositoryInterface
import com.example.androidd07.network.ApiInterFace
import com.example.androidd07.network.RemoteSource
import com.example.androidd07.network.RetrofitHelper
import com.example.androidd07.product.viewmodel.ProductFactory
import com.example.androidd07.product.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appmodule = module {
    single<ApiInterFace>{
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dummyjson.com/")
            .build().create(ApiInterFace::class.java)
    }
    single <RemoteSource>{
        RetrofitHelper(get())
    }
    single<LocalSource> {
        ConcreteLocalSource.getInstance(get())
    }
    single <RepositoryInterface>{
        Repository.getInstance(get(),get())
    }
    factory {
        ProductFactory(get())
    }
    viewModel {
        ProductViewModel(get())
    }
    factory {
        FavViewModelFactory(get())
    }
    viewModel {
        FavViewModel(get())
    }

}