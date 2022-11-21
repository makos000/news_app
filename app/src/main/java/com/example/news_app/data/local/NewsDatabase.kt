package com.example.news_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [NewsEntity::class],
    version = 3,
    exportSchema = false
)

//todo:this convertor is not necessary
@TypeConverters(RoomConverter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}