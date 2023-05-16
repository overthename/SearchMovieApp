package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.model.SearchResponse
import retrofit2.Response

interface ShopSearchRepository {

    suspend fun searchShops(
        query: String,
        start: Int,
    ): Response<SearchResponse>


}