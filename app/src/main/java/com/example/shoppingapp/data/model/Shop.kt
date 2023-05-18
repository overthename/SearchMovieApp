package com.example.shoppingapp.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

// 지금 처럼 싱글 모듈인 상황에서는 괜찮지만 실제 프로젝트에서는 응답 객체로 받을 Data Class를 Parcelable로 지정할 수 없을것이와요
// 프로젝트에서는 api를 요청하는 모듈과 view를 다루는 모듈이 분리되기 때문이에요
// 그래서 얘를 바로 쓰기보다는 view를 구성하는데 꼭 필요한 데이터만 모아서 class를 만든 뒤 걔를 parcelable로 지정해주는게 좋읍니다.
@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "shops")
data class Shop(
    @field:Json(name = "brand")
    val brand: String,
    @field:Json(name = "category1")
    val category1: String,
    @field:Json(name = "category2")
    val category2: String,
    @field:Json(name = "category3")
    val category3: String,
    @field:Json(name = "category4")
    val category4: String,
    @field:Json(name = "hprice")
    val hprice: String,
    @field:Json(name = "image")
    val image: String,
    @field:Json(name = "link")
    val link: String,
    @field:Json(name = "lprice")
    val lprice: String,
    @field:Json(name = "maker")
    val maker: String,
    @field:Json(name = "mallName")
    val mallName: String,
    @PrimaryKey(autoGenerate = false)
    @field:Json(name = "productId")
    val productId: String,
    @field:Json(name = "productType")
    val productType: String,
    @field:Json(name = "title")
    val title: String
) : Parcelable
