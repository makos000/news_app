package com.example.news_app.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.data.local.NewsEntity
import com.example.news_app.data.repo.RepoInterface
import com.example.news_app.domain.model.Data
import com.example.news_app.domain.model.DataModel
import com.example.news_app.domain.model.NewsModel
import com.example.news_app.domain.model.toNewsEntity
import com.example.news_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RepoInterface
) : ViewModel() {
    private var _data: MutableStateFlow<Resource<List<NewsEntity>>> =
        MutableStateFlow(Resource.Loading())
    var data: StateFlow<Resource<List<NewsEntity>>> = _data

    var newsScreen = true


    var article = DataModel()
    // var article = Data().toNewsEntity()
    // var article = Data()

    fun getData(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNews(category).collect() {
                _data.value = it
            }
        }
    }

    private val _isLoading = MutableStateFlow(false)
    val isloading = _isLoading.asStateFlow()

    init {
        loadStuff()
    }

    fun loadStuff() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000L)
            _isLoading.value = false
        }
    }
}