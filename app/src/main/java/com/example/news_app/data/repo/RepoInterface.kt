package com.example.news_app.data.repo

import com.example.news_app.data.local.NewsEntity
import com.example.news_app.data.remote.RemoteDataSourceInterface
import com.example.news_app.domain.model.Data
import com.example.news_app.domain.model.NewsModel
import com.example.news_app.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepoInterface {
    suspend fun getNews(category: String): Flow<Resource<List<NewsEntity>>>

    fun insertNewsToDB(newsEntity: List<NewsEntity>)

    fun readNewsFromDB(): Flow<List<NewsEntity>>

    fun nukeTable()
    suspend fun fetchDataFromRemote(
        category: String
    ): Resource<List<Data>>
}