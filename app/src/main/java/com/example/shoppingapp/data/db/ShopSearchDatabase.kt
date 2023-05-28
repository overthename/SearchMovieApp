package com.example.shoppingapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.data.model.Shop


@Database(
    entities = [Shop::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(OrmConverter::class)
abstract class ShopSearchDatabase : RoomDatabase() {

    abstract fun shopSearchDao(): ShopSearchDao


}