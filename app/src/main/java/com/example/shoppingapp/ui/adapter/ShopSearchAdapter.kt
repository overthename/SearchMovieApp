package com.example.shoppingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.booksearch.ui.adapter.ShopSearchViewHolder
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.databinding.ItemShopPreviewBinding

class ShopSearchAdapter :
    androidx.recyclerview.widget.ListAdapter<Shop, ShopSearchViewHolder>(BookDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopSearchViewHolder {
        return ShopSearchViewHolder(
            ItemShopPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: ShopSearchViewHolder, position: Int) {
        val shop: Shop = currentList[position]
        holder.bind(shop)
//        holder.itemView.setOnClickListener {
//            onItemClickListener?.let { it(book) }
//        }
    }

//    // API를 통해 받아온 책을 아이템으로, 각각의 아이템을 클릭 시 동작을 수행하는 리스너 설정
//    private var onItemClickListener: ((Book) -> Unit)? = null
//    fun setOnItemClickListener(listener: (Book) -> Unit) {
//        onItemClickListener = listener
//    }

    // DiffUtil 작동을 위한 callback 생성
    companion

    object {
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