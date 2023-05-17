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
    androidx.recyclerview.widget.ListAdapter<Shop, RecyclerView.ViewHolder>(BookDiffCallback) {
    private val items = ArrayList<Shop>()
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].productId) {
            "" -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemShopPreviewBinding.inflate(layoutInflater, parent, false)
                ShopSearchViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)}
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val shop: Shop = currentList[position]
//        Log.e("position",items[position].toString())

        if(holder is ShopSearchViewHolder){
            holder.bind(items[position])
        }else{

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun setList(shop: MutableList<Shop>) {
        items.addAll(shop)
        items.add(Shop(" ", " ","","","","","","","","","","","",""))
    }

    fun deleteLoading(){
        items.removeAt(items.lastIndex)
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