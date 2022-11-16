package com.example.news_app.di

import android.content.Context
import androidx.room.Room
import com.example.news_app.data.remote.api.ApiInterface
import com.example.news_app.data.remote.api.ApiRes
import com.example.news_app.data.repo.RepoImpl
import com.example.news_app.data.repo.RepoInterface
import com.example.news_app.data.local.NewsDao
import com.example.news_app.data.local.NewsDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun retrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiRes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun getApiDetail(retrofit: Retrofit): ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun getRepo(apiInterface: ApiInterface, newsDao: NewsDao): RepoInterface {
        return RepoImpl(apiInterface, newsDao)
    }

    @Provides
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        "NewsDatabase",
    ).build()

    @Provides
    fun provideDao(database: NewsDatabase) = database.newsDao()

}