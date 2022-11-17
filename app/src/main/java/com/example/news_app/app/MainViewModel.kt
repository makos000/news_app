package com.example.news_app.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.data.local.NewsEntity
import com.example.news_app.data.repo.RepoInterface
import com.example.news_app.domain.model.NewsModel
import com.example.news_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: RepoInterface): ViewModel(){

    private var _data: MutableStateFlow<Resource<List<NewsEntity>>> = MutableStateFlow(Resource.Loading())
    var data: StateFlow<Resource<List<NewsEntity>>> = _data

    var article = NewsModel()

    fun getData(category: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNews(category).collect(){
                _data.value = it
            }
        }
    }
}