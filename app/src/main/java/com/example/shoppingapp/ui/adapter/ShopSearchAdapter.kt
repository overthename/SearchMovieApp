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
import com.example.shoppingapp.data.model.LikeShop
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.databinding.ItemLoadingBinding
import com.example.shoppingapp.databinding.ItemShopPreviewBinding
import com.example.shoppingapp.ui.view.SearchFragment

class ShopSearchAdapter(private val onLikeStateChanged : (LikeShop, Boolean) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<LikeShop, ShopSearchAdapter.ShopSearchViewHolder>(BookDiffCallback) {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    class ShopSearchViewHolder(
        private val binding: ItemShopPreviewBinding,
        itemClickListener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        var toggle = binding.tbLike

        fun bind(shop: LikeShop) {
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
                binding.tbLike.isChecked= shop.isLiked
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopSearchViewHolder {
        return ShopSearchViewHolder(
            ItemShopPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ShopSearchViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.toggle.setOnClickListener {
            if(holder.toggle.isChecked){
                onLikeStateChanged(getItem(position),true)
            }else{
                onLikeStateChanged(getItem(position),false)
            }
        }
    }

    companion object {
        private val BookDiffCallback: DiffUtil.ItemCallback<LikeShop> =
            object : DiffUtil.ItemCallback<LikeShop>() {
                override fun areItemsTheSame(oldItem: LikeShop, newItem: LikeShop): Boolean {
                    return oldItem.productId == newItem.productId
                }

                override fun areContentsTheSame(oldItem: LikeShop, newItem: LikeShop): Boolean {
                    return oldItem == newItem
                }
            }
    }


}