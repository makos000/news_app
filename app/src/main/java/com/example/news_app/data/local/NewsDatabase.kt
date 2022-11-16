package com.example.news_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RoomConverter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}