package com.example.shoppingapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "display")
    val display: Int,
    @Json(name = "items")
    val items: List<Shop>,
    @Json(name = "lastBuildDate")
    val lastBuildDate: String,
    @Json(name = "start")
    val start: Int,
    @Json(name = "total")
    val total: Int
)