package com.example.shoppingapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapp.data.repository.ShopSearchRepository

@Suppress("UNCHECKED_CAST")
class ShopSearchViewModelFactory(
    private val shopSearchRepository: ShopSearchRepository
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(shopSearchRepository) as T
        }
        throw IllegalArgumentException("ViewModel class Not found")
    }
}