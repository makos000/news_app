package com.example.news_app.domain.model


import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("category")
    val category: String = "",
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("success")
    val success: Boolean = false
)