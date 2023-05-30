package com.example.shoppingapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppingapp.data.model.LikeShop

@Dao
interface ShopSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShop(shop : LikeShop)

    @Delete
    suspend fun deleteShop(shop : LikeShop)

    @Query("SELECT * FROM likeshops")
     fun getLikeShops(): LiveData<List<LikeShop>>

    @Query("SELECT productId FROM likeshops")
    suspend fun getLikeShopsId(): List<String>

    @Query("SELECT * FROM likeshops WHERE productId=:id")
    suspend fun isLikeShop(id: String) : LikeShop?


}