package com.example.shoppingapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Shop(
    @Json(name = "brand")
    val brand: String,
    @Json(name = "category1")
    val category1: String,
    @Json(name = "category2")
    val category2: String,
    @Json(name = "category3")
    val category3: String,
    @Json(name = "category4")
    val category4: String,
    @Json(name = "hprice")
    val hprice: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "lprice")
    val lprice: String,
    @Json(name = "maker")
    val maker: String,
    @Json(name = "mallName")
    val mallName: String,
    @Json(name = "productId")
    val productId: String,
    @Json(name = "productType")
    val productType: String,
    @Json(name = "title")
    val title: String
) : Parcelable