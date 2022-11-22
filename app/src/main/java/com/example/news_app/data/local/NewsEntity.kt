package com.example.news_app.data.local

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news_app.domain.model.Data
import com.example.news_app.domain.model.DataModel
import com.example.news_app.domain.model.NewsModel
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "news_table")
//class NewsEntity(val newsModel: NewsModel) {
//    @PrimaryKey(autoGenerate = true)
//    var index : Int = 0
//}

@Entity(tableName = "news_table")
data class NewsEntity(
    val author: String? = "",
    val content: String? = "",
    val date: String? = "",
    val id: String? = "",
    val imageUrl: String? = "",
    val readMoreUrl: String? = "",
    val time: String? = "",
    val title: String? = "",
    val url: String? = "",
    @PrimaryKey(autoGenerate = true)
    var index : Int = 0
){
    //todo This is important when we use model to display to ui
    fun toDataModel(): DataModel {
        return DataModel(
            author = author?:"",
            content = content?:"",
            date = date?:"",
            imageUrl = imageUrl?:"",
            readMoreUrl = readMoreUrl?:"",
            time = time?:"",
            title = title?:"",
            url = url?:""
        )
    }
    //todo This is important when we use model to display to ui
    fun toData(): Data {
        return Data(
            author = author?:"",
            content = content?:"",
            date = date?:"",
            id=id?:"",
            imageUrl = imageUrl?:"",
            readMoreUrl = readMoreUrl?:"",
            time = time?:"",
            title = title?:"",
            url = url?:""
        )
    }
}
