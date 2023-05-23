package com.example.shoppingapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.databinding.ItemShopPreviewBinding

class ShopSearchViewHolder(
    private val binding: ItemShopPreviewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(shop: Shop) {
        var title=""
        if(shop.title.contains('<')){
            title = shop.title.substring(0,shop.title.indexOf('<'))
        }else{
            title = shop.title
        }
        itemView.apply {
            binding.ivShopImage.load(shop.image)
            binding.tvTitle.text = title
            binding.tvBrand.text = shop.brand
            binding.tvLprice.text = shop.lprice
        }
    }

}