package com.example.booksearch.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.databinding.ItemShopPreviewBinding

class ShopSearchViewHolder(
    private val binding: ItemShopPreviewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(shop: Shop) {
//        val author = book.authors.toString().removeSurrounding("[", "]")
//        val publisher = book.publisher
//        val date = if (book.datetime.isNotEmpty()) book.datetime.substring(0, 10) else ""

        itemView.apply {
            binding.ivShopImage.load(shop.image)
            binding.tvTitle.text = shop.title
            binding.tvBrand.text = shop.brand
            binding.tvLprice.text = shop.lprice
        }
    }

}