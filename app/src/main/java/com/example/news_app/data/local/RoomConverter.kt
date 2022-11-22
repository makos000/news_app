package com.example.news_app.data.local

import androidx.room.TypeConverter
import com.example.news_app.domain.model.Data
import com.example.news_app.domain.model.NewsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverter {
    var gson = Gson()

    @TypeConverter
    fun newsToString(data: Data): String = gson.toJson(data)

    @TypeConverter
    fun stringToNews(data: String): Data {
        val listType = object : TypeToken<Data>() {}.type
        return gson.fromJson(data, listType)
    }
}