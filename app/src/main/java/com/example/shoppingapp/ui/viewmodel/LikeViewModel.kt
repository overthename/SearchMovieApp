package com.example.shoppingapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.LikeShop
import com.example.shoppingapp.data.repository.ShopSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LikeViewModel@Inject constructor(
    private val shopSearchRepository: ShopSearchRepository
) : ViewModel(){

        val likeShops :LiveData<List<LikeShop>> =  shopSearchRepository.getLikeShops()

        fun deleteShop(shop: LikeShop) {
            viewModelScope.launch(Dispatchers.IO) {
                shopSearchRepository.deleteShops(shop)
            }
        }

    }


