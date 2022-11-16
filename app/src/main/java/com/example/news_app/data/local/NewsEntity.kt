package com.example.news_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news_app.domain.model.NewsModel

@Entity(tableName = "news_table")
class NewsEntity(val newsModel: NewsModel) {
    @PrimaryKey(autoGenerate = true)
    var index : Int = 0
}