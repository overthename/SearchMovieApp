package com.example.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import com.example.shoppingapp.data.model.LikeShop
import com.example.shoppingapp.data.model.SearchResponse
import retrofit2.Response

interface ShopSearchRepository {

    suspend fun searchShops(
        query: String,
        display: Int,
        start: Int,
    ): Response<SearchResponse>

    suspend fun insertShops(shop: LikeShop)

    suspend fun deleteShops(shop: LikeShop)

    fun getLikeShops(): LiveData<List<LikeShop>>

    suspend fun isLikeShop(productId : String) : Boolean

    suspend fun getLikeShopsId(): List<String>
}