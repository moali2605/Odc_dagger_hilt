package com.example.androidd07.product.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.androidd07.databinding.ActivityProductBinding
import com.example.androidd07.db.ConcreteLocalSource
import com.example.androidd07.model.Repo.ApiState
import com.example.androidd07.model.Repo.Repository
import com.example.androidd07.network.RetrofitHelper
import com.example.androidd07.product.viewmodel.ProductFactory
import com.example.androidd07.product.viewmodel.ProductViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductBinding
    lateinit var recyclerAdapter: RecyclerAdapter
    private val productViewModel:ProductViewModel by lazy { getViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerAdapter = RecyclerAdapter(this) {
            productViewModel.addToFav(it)
        }
        binding.rvProduct.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@ProductActivity).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }

        lifecycleScope.launch {
            productViewModel.liveProduct.collectLatest {
                when (it) {
                    is ApiState.Loading ->{

                        Toast.makeText(this@ProductActivity,"Loading",Toast.LENGTH_LONG).show()
                    }
                    is ApiState.Success -> {
                        binding.ivpb.visibility=View.GONE
                        recyclerAdapter.submitList(it.list)
                    }
                    else->{
                        Toast.makeText(this@ProductActivity,"Oooops",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        binding.rvProduct.itemAnimator = SlideInItemAnimator()
    }
}

class SlideInItemAnimator : SimpleItemAnimator() {
    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {
        holder.itemView.alpha = 0f
        holder.itemView.translationX = holder.itemView.width.toFloat()
        holder.itemView.animate()
            .alpha(1f)
            .translationX(0f)
            .setDuration(1000)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    dispatchAddFinished(holder)
                }
            })
            .start()
        return false
    }


    override fun animateMove(
        holder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromLeft: Int,
        fromTop: Int,
        toLeft: Int,
        toTop: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun runPendingAnimations() {
        TODO("Not yet implemented")
    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {
        TODO("Not yet implemented")
    }

    override fun endAnimations() {
        TODO("Not yet implemented")
    }

    override fun isRunning(): Boolean {
        TODO("Not yet implemented")
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder): Boolean {
        return false
    }
}