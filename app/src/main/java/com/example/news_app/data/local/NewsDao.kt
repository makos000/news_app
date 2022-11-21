package com.example.news_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertNewsToDB(newsEntity: List<NewsEntity>)
    //  fun insertNewsToDB(newsEntity: NewsEntity)

    @Query("SELECT * FROM news_table order by `index` DESC")
    fun readNewsFromDB(): Flow<List<NewsEntity>>

    @Query("DELETE FROM news_table")
    fun nukeTable()
}