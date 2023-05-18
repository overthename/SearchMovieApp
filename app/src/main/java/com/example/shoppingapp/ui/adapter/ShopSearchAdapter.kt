package com.example.shoppingapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.databinding.ItemLoadingBinding
import com.example.shoppingapp.databinding.ItemShopPreviewBinding

class ShopSearchAdapter :
    androidx.recyclerview.widget.ListAdapter<Shop, ShopSearchViewHolder>(BookDiffCallback) {
    // submitList를 활용하면 직접 관리 안해줘도 괜찮아요
    private val items = ArrayList<Shop>()
    // 로딩 UI를 넣으려했군요 좋읍니다.
    // 보통 이런 상수는 const로 선언하죠
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
//    override fun getItemViewType(position: Int): Int {
//        return when (items[position].productId) {
//            "" -> VIEW_TYPE_LOADING
//            else -> VIEW_TYPE_ITEM
//        }
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ShopSearchViewHolder {
//        return when (viewType) {
//            VIEW_TYPE_ITEM -> {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ItemShopPreviewBinding.inflate(layoutInflater, parent, false)
//                ShopSearchViewHolder(binding)
//            }
//            else -> {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
//                LoadingViewHolder(binding)}
//        }
        return ShopSearchViewHolder(
            ItemShopPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: ShopSearchViewHolder, position: Int) {
//        val shop: Shop = currentList[position]
        Log.e("test3",items[position].toString())

//        if(holder is ShopSearchViewHolder){
//            holder.bind(items[position])
//        }else{
//
//        }
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun setList(shop: MutableList<Shop>) {
        items.addAll(shop)
//        items.add(Shop("", "","","","","","","","","","","","",""))
    }

    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }


    // 지금 상태에서는 이게 활용되고 있진 않겠군요
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
