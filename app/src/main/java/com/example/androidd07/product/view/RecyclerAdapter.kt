package com.example.androidd07.product.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidd07.R
import com.example.androidd07.databinding.ProListItemBinding
import com.example.androidd07.model.Dto.ProductDto

class RecyclerAdapter(var context:Context, private val onClick: (ProductDto) -> Unit):ListAdapter<ProductDto,ProductViewHolder>(ProductDiffUtil()) {
    lateinit var binding:ProListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ProListItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem: ProductDto = getItem(position)
        Glide.with(context)
            .load(currentItem.thumbnail)
            .into(holder.binding.ivPhoto)
        holder.binding.tvTitle.text = currentItem.title
        holder.binding.tvPrice.text=currentItem.price.toString()
        holder.binding.tvBrand.text=currentItem.brand
        holder.binding.tvDescription.text=currentItem.description
        holder.binding.ratingBar.rating=currentItem.rating.toFloat()
        holder.binding.btnFavourite.setOnClickListener {
            onClick(currentItem)
        }
    }
}

class ProductViewHolder(var binding: ProListItemBinding) : RecyclerView.ViewHolder(binding.root)


class ProductDiffUtil : DiffUtil.ItemCallback<ProductDto>() {
    override fun areItemsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
        return oldItem.equals(newItem)
    }

}