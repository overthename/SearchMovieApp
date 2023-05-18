package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.api.ShopSearchApi
import com.example.shoppingapp.data.model.SearchResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopSearchRepositoryImpl @Inject constructor(
    private val api: ShopSearchApi
): ShopSearchRepository {

    override suspend fun searchShops(
        query: String,
        display: Int,
        start: Int,
    ): Response<SearchResponse>{
        return api.searchShops(query,display, start)
    }


}