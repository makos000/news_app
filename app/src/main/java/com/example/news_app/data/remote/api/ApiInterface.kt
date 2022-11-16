package com.example.news_app.data.remote.api

import com.example.news_app.domain.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(ApiRes.END_POINT)
    suspend fun getNews(
        @Query("category") category: String
    ):Response<NewsModel>
}