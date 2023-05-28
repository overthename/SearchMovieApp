package com.example.shoppingapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppingapp.data.model.Shop

@Dao
interface ShopSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShop(shop : Shop)

    @Delete
    suspend fun deleteShop(shop : Shop)

    @Query("SELECT * FROM shops")
    fun getLikeShops(): LiveData<List<Shop>>
}