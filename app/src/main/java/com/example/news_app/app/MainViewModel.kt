package com.example.news_app.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.App
import com.example.news_app.data.local.NewsEntity
import com.example.news_app.data.repo.RepoInterface
import com.example.news_app.domain.model.DataModel
import com.example.news_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application,
    private val repository: RepoInterface
) : AndroidViewModel(app) {
    private var _data: MutableStateFlow<Resource<List<NewsEntity>>> =
        MutableStateFlow(Resource.Loading())
    var data: StateFlow<Resource<List<NewsEntity>>> = _data

    var newsScreen = true


    var article = DataModel()
    // var article = Data().toNewsEntity()
    // var article = Data()

    fun getData(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (hasInternetConnection()) {
                repository.nukeTable()
                repository.getNews(category).collect() {
                    _data.value = it
                }
            } else {
                repository.getNews(category).collect() {
                    _data.value = it
                }
            }
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<App>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}