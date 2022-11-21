package com.example.news_app.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun insertNewsToDB(newsEntity: NewsEntity)

    fun readNewsFromDB(): Flow<List<NewsEntity>>

    fun nukeTable()

}