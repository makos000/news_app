package com.example.news_app.data.remote

import com.example.news_app.domain.model.NewsModel
import com.example.news_app.util.Resource

interface RemoteDataSourceInterface {
    suspend fun getNews(category:String):Resource<NewsModel>
}