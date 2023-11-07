package com.example.androidd07.Favourite.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidd07.databinding.FavListItemBinding
import com.example.androidd07.model.Dto.ProductDto

class FavRecyclerAdapter(var context: Context, private val onClick: (ProductDto) -> Unit):
    ListAdapter<ProductDto, ProductViewHolder>(ProductDiffUtil()) {
    lateinit var binding: FavListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = FavListItemBinding.inflate(inflater, parent, false)
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
        holder.binding.btnDelete.setOnClickListener {
            onClick(currentItem)
        }
    }
}

class ProductViewHolder(var binding: FavListItemBinding) : RecyclerView.ViewHolder(binding.root)


class ProductDiffUtil : DiffUtil.ItemCallback<ProductDto>() {
    override fun areItemsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
        return oldItem.equals(newItem)
    }

}
