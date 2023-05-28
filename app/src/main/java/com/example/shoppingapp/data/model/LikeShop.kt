package com.example.shoppingapp.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "like")
data class LikeShop(
    @field:Json(name = "brand")
    val brand: String,
    @field:Json(name = "image")
    val image: String,
    @field:Json(name = "lprice")
    val lprice: String,
    @PrimaryKey(autoGenerate = false)
    @field:Json(name = "productId")
    val productId: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "isChecked")
    val isChecked: Int
) : Parcelable