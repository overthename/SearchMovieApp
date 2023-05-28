package com.example.shoppingapp.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.data.repository.ShopSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LikeViewModel@Inject constructor(
    private val shopSearchRepository: ShopSearchRepository
) : ViewModel(){

    val likeShops: LiveData<List<Shop>> = shopSearchRepository.getLikeShops()


    //Room
    fun saveShop(shop: Shop) = viewModelScope.launch(Dispatchers.IO) {
        shopSearchRepository.insertShops(shop)
    }

    fun deleteShop(shop: Shop) = viewModelScope.launch(Dispatchers.IO) {
        shopSearchRepository.deleteShops(shop)
    }


}