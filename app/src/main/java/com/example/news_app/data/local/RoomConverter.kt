package com.example.news_app.data.local

import androidx.room.TypeConverter
import com.example.news_app.domain.model.NewsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverter {
    var gson = Gson()

    @TypeConverter
    fun newsToString(newsModel: NewsModel): String = gson.toJson(newsModel)

    @TypeConverter
    fun stringToNews(data: String): NewsModel{
        val listType = object : TypeToken<NewsEntity>() {}.type
        return gson.fromJson(data, listType)
    }
}