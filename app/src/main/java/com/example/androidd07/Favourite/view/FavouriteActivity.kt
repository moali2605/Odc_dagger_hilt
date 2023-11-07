package com.example.androidd07.Favourite.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidd07.Favourite.viewmodel.FavViewModel
import com.example.androidd07.Favourite.viewmodel.FavViewModelFactory
import com.example.androidd07.R
import com.example.androidd07.databinding.ActivityFavouriteBinding
import com.example.androidd07.db.ConcreteLocalSource
import com.example.androidd07.model.Repo.Repository
import com.example.androidd07.network.RetrofitHelper
import com.example.androidd07.product.viewmodel.ProductViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FavouriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityFavouriteBinding
    lateinit var favRecyclerAdapter: FavRecyclerAdapter
    private val favViewModel: FavViewModel by lazy { getViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        favRecyclerAdapter = FavRecyclerAdapter(this) {
            favViewModel.deleteFav(it)
        }
        binding.rvFavourite.apply {
            adapter = favRecyclerAdapter
            layoutManager = LinearLayoutManager(this@FavouriteActivity).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }
        lifecycleScope.launch() {
            favViewModel.stateFavProduct.collect {
                favRecyclerAdapter.submitList(it)
            }
        }
    }
}