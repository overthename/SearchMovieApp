package com.example.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import com.example.shoppingapp.data.model.SearchResponse
import com.example.shoppingapp.data.model.Shop
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ShopSearchRepository {

    suspend fun searchShops(
        query: String,
        display: Int,
        start: Int,
    ): Response<SearchResponse>

    suspend fun insertShops(shop: Shop)

    suspend fun deleteShops(shop: Shop)

    fun getLikeShops(): LiveData<List<Shop>>

}