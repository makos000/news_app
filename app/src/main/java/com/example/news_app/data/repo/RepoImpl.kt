package com.example.news_app.data.repo

import com.example.news_app.api.ApiInterface
import com.example.news_app.data.room.NewsDao
import com.example.news_app.data.room.NewsEntity
import com.example.news_app.domain.model.NewsModel
import com.example.news_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepoImpl @Inject constructor(val apiInterface: ApiInterface, val newsDao: NewsDao): RepoInterface {
    override suspend fun getNews(category : String): Flow<Resource<List<NewsEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiInterface.getNews(category)
            if (response.isSuccessful){
                insertNewsToDB(NewsEntity(response.body()!!))
                readNewsToDB().collect(){
                    emit(Resource.Success(it))
                }
            }
            else{
                emit(Resource.Error(response.message()))
            }
        } catch (e: HttpException){
            emit(Resource.Error("Could not load"))
        } catch (e: IOException){
            emit(Resource.Error("Internet connection issue"))
        }
    }

    override fun insertNewsToDB(newsEntity: NewsEntity) {
        return newsDao.insertNewsToDB(newsEntity)
    }

    override fun readNewsToDB(): Flow<List<NewsEntity>> {
        return newsDao.readNewsFromDB()
    }

    override fun nukeTable() {
        return newsDao.nukeTable()
    }
}