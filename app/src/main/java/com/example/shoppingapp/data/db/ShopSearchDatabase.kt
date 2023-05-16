package com.example.shoppingapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//
//
//@Database(
//    entities = [RecentSearch::class],
//    version = 1,
//    exportSchema = false
//)

abstract class ShopSearchDatabase : RoomDatabase() {

//    abstract fun shopSearchDao(): ShopSearchDao

//    companion object {
//        @Volatile
//        private var INSTANCE: ShopSearchDatabase? = null
//
//        private fun buildDatabase(context: Context): ShopSearchDatabase =
//            Room.databaseBuilder(
//                context.applicationContext,
//                ShopSearchDatabase::class.java,
//                "recent-search"
//            ).build()
//
//        fun getInstance(context: Context): ShopSearchDatabase =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
//    }
}