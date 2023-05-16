package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.api.RetrofitInstance.api
import com.example.shoppingapp.data.model.SearchResponse
import retrofit2.Response

class ShopSearchRepositoryImpl : ShopSearchRepository {

    override suspend fun searchShops(
        query: String,
        display: Int,
        start: Int,
    ): Response<SearchResponse>{
        return api.searchShops(query, display, start)
    }


}