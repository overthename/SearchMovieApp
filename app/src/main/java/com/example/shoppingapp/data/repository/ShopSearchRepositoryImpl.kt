package com.example.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import com.example.shoppingapp.data.api.ShopSearchApi
import com.example.shoppingapp.data.db.ShopSearchDatabase
import com.example.shoppingapp.data.model.SearchResponse
import com.example.shoppingapp.data.model.Shop
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopSearchRepositoryImpl @Inject constructor(
    private val db: ShopSearchDatabase,
    private val api: ShopSearchApi
): ShopSearchRepository {

    override suspend fun searchShops(
        query: String,
        display: Int,
        start: Int,
    ): Response<SearchResponse>{
        return api.searchShops(query,display, start)
    }

    override suspend fun insertShops(shop: Shop) {
        db.shopSearchDao().insertShop(shop)
    }

    override suspend fun deleteShops(shop: Shop) {
        db.shopSearchDao().deleteShop(shop)
    }

    override fun getLikeShops(): LiveData<List<Shop>> {
        return db.shopSearchDao().getLikeShops()
    }
}