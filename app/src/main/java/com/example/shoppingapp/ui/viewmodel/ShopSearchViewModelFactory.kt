package com.example.shoppingapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapp.data.repository.ShopSearchRepository

// Hilt를 통해 repository를 주입해주기 때문에 요건 필요없을 텝니다
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
