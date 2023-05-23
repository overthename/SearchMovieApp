package com.example.shoppingapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.databinding.ItemLoadingBinding
import com.example.shoppingapp.databinding.ItemShopPreviewBinding

class ShopSearchAdapter :
    androidx.recyclerview.widget.ListAdapter<Shop, ShopSearchViewHolder>(BookDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ShopSearchViewHolder {
        return ShopSearchViewHolder(
            ItemShopPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: ShopSearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        private val BookDiffCallback: DiffUtil.ItemCallback<Shop> =
            object : DiffUtil.ItemCallback<Shop>() {
                override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean {
                    return oldItem.productId == newItem.productId
                }

                override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean {
                    return oldItem == newItem
                }
            }
    }
}