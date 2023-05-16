package com.example.shoppingapp.data.api

import com.example.shoppingapp.data.model.SearchResponse
import com.example.shoppingapp.util.Constants.CLIENT_ID
import com.example.shoppingapp.util.Constants.CLIENT_SECRET
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ShopSearchApi {
    @Headers(
        "X-Naver-Client-Id: $CLIENT_ID",
        "X-Naver-Client-Secret: $CLIENT_SECRET"
    )
    @GET("v1/search/shop.json")
    suspend fun searchShops(
        @Query("query") query: String,
        @Query("start") start: Int
    ): Response<SearchResponse>
}