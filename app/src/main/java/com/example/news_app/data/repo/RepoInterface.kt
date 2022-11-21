package com.example.news_app.data.repo

import com.example.news_app.data.local.NewsEntity
import com.example.news_app.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepoInterface {
    suspend fun getNews(category: String): Flow<Resource<List<NewsEntity>>>

    fun insertNewsToDB(newsEntity: NewsEntity)

    fun readNewsFromDB(): Flow<List<NewsEntity>>

    fun nukeTable()
}