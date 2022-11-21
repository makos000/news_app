package com.example.news_app.data.repo

import com.example.news_app.data.local.NewsDao
import com.example.news_app.data.local.NewsEntity
import com.example.news_app.data.remote.RemoteDataSourceImpl
import com.example.news_app.data.remote.RemoteDataSourceInterface
import com.example.news_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepoImpl @Inject constructor(val remote: RemoteDataSourceInterface, val local: NewsDao) :
    RepoInterface {
    override suspend fun getNews(category: String): Flow<Resource<List<NewsEntity>>> = flow {
        emit(Resource.Loading())
        readNewsFromDB().collect() { lists ->
            if (lists.isEmpty()) {
                val response = remote.getNews(category)
                if (response is Resource.Success) {
                    insertNewsToDB(NewsEntity(response.data!!))
                    readNewsFromDB().collect() {
                        emit(Resource.Success(it))
                    }
                } else {
                    emit(Resource.Error(response.message!!))
                }
            } else {

                readNewsFromDB().collect() { lists ->
                    emit(Resource.Success(lists))
                }
            }
        }

    }

    override fun insertNewsToDB(newsEntity: NewsEntity) {
        return local.insertNewsToDB(newsEntity)
    }

    override fun readNewsFromDB(): Flow<List<NewsEntity>> {
        return local.readNewsFromDB()
    }

    override fun nukeTable() {
        return local.nukeTable()
    }
}