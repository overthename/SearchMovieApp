package com.example.booksearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
//
//class ShopSearchPagingAdapter : PagingDataAdapter<Book, ShopSearchViewHolder>(BookDiffCallback) {

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopSearchViewHolder {
//        return ShopSearchViewHolder(
//            ItemBookPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: ShopSearchViewHolder, position: Int) {
//        val pageBook = getItem(position) //null 처리
//        pageBook?.let { book ->
//            holder.bind(book)
//            holder.itemView.setOnClickListener {
//                onItemClickListener?.let { it(book) }
//            }
//        }
//
//    }
//
//    private var onItemClickListener: ((Book) -> Unit)? = null
//    fun setOnItemClickListener(listener: (Book) -> Unit) {
//        onItemClickListener = listener
//    }
//
//    companion object {
//        private val BookDiffCallback = object : DiffUtil.ItemCallback<Book>() {
//            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
//                return oldItem.isbn == newItem.isbn
//            }
//
//            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
//                return oldItem == newItem
//            }
//
//        }
//    }
//}