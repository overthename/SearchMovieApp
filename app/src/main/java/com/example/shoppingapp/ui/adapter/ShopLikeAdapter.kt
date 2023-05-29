package com.example.shoppingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoppingapp.data.model.LikeShop
import com.example.shoppingapp.databinding.ItemShopPreviewBinding
import com.example.shoppingapp.ui.view.LikeFragment

class ShopLikeAdapter(private val onDelete : (LikeShop) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<LikeShop, ShopLikeAdapter.ShopSearchViewHolder>(BookDiffCallback) {

    class ShopSearchViewHolder(
        private val binding: ItemShopPreviewBinding,
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
            ItemShopPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShopSearchViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.toggle.setOnClickListener {
            if(!holder.toggle.isChecked){
                onDelete(getItem(position))
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