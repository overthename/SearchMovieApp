package com.example.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import com.example.shoppingapp.data.api.ShopSearchApi
import com.example.shoppingapp.data.db.ShopSearchDatabase
import com.example.shoppingapp.data.model.LikeShop
import com.example.shoppingapp.data.model.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    override suspend fun insertShops(shop: LikeShop) {
        db.shopSearchDao().insertShop(shop)
    }

    override suspend fun deleteShops(shop: LikeShop) {
        db.shopSearchDao().deleteShop(shop)
    }

    override fun getLikeShops(): LiveData<List<LikeShop>> {
        return db.shopSearchDao().getLikeShops()
    }

    override suspend fun getLikeShopsId(): List<String> {
        return db.shopSearchDao().getLikeShopsId()
    }

    override suspend fun isLikeShop(productId: String): Boolean {
        return withContext(Dispatchers.IO) {
            db.shopSearchDao().isLikeShop(productId) != null
        }
    }




}