package com.example.shoppingapp.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "likeshops")
data class LikeShop(
    val brand: String,
    val image: String,
    val lprice: String,
    @PrimaryKey(autoGenerate = false)
    val productId: String,
    val title: String,
    val isLiked: Boolean,
) : Parcelable