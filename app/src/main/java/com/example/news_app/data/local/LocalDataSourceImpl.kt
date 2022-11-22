package com.example.news_app.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: NewsDao
):LocalDataSource
{
    override fun insertNewsToDB(newsEntity: List<NewsEntity>) {
        return dao.insertNewsToDB(newsEntity)
    }

    override fun readNewsFromDB(): Flow<List<NewsEntity>> {
        return dao.readNewsFromDB()
    }

    override fun nukeTable() {
        return dao.nukeTable()
    }
}