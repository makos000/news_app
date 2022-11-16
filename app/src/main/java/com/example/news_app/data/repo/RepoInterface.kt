package com.example.news_app.data.repo

import androidx.room.Entity
import com.example.news_app.data.room.NewsEntity
import com.example.news_app.domain.model.NewsModel
import com.example.news_app.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepoInterface {
    suspend fun getNews(category: String): Flow<Resource<List<NewsEntity>>>

    fun insertNewsToDB(newsEntity: NewsEntity)

    fun readNewsToDB(): Flow<List<NewsEntity>>

    fun nukeTable()
}