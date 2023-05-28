package com.example.shoppingapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoppingapp.R
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.databinding.ItemLoadingBinding
import com.example.shoppingapp.databinding.ItemShopPreviewBinding
import com.example.shoppingapp.ui.view.LikeFragment
import com.example.shoppingapp.ui.view.SearchFragment

class ShopLikeAdapter(val fragment : LikeFragment) :
    androidx.recyclerview.widget.ListAdapter<Shop, ShopLikeAdapter.ShopSearchViewHolder>(BookDiffCallback) {

    class ShopSearchViewHolder(
        private val binding: ItemShopPreviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        var toggle = binding.tbLike

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
             //   binding.tbLike.isChecked=
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopSearchViewHolder {
        return ShopSearchViewHolder(
            ItemShopPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShopSearchViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.toggle.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                fragment.saveLike(getItem(position))
            }
        }
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