package com.example.shoppingapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.data.model.LikeShop


@Database(
    entities = [LikeShop::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(OrmConverter::class)
abstract class ShopSearchDatabase : RoomDatabase() {

    abstract fun shopSearchDao(): ShopSearchDao


}