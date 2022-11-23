package com.example.news_app.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news_app.app.news.DetailScreen
import com.example.news_app.app.news.MainViewModel
import com.example.news_app.app.news.NewsScreen
import com.example.news_app.app.theme.News_appTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            News_appTheme {
                setContent { NewsApp() }
            }
        }
    }
/*
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun NewsApp2() {
        var showNewsScreen by rememberSaveable { mutableStateOf(viewModel.newsScreen) }

        if(showNewsScreen){
            NewsScreen(viewModel = viewModel, onClicked = {viewModel.newsScreen = false
            showNewsScreen = viewModel.newsScreen})
        }
        else{
            DetailScreen(viewModel = viewModel, onClicked = {viewModel.newsScreen = true
            showNewsScreen = viewModel.newsScreen})
        }
    }
*/



    @Composable
    fun Navigation(navController: NavHostController){

        var showNewsScreen by rememberSaveable { mutableStateOf(viewModel.newsScreen) }

        NavHost(navController = navController, startDestination = "home" ){
            composable("home") {
                if(showNewsScreen){
                    NewsScreen(viewModel = viewModel,
                        onClicked = {viewModel.newsScreen = false
                        showNewsScreen = viewModel.newsScreen},
                        openScreen ={ route -> navController.navigate(route) }
                    )
                }
                else{
                    DetailScreen(viewModel = viewModel, onClicked = {viewModel.newsScreen = true
                        showNewsScreen = viewModel.newsScreen})
                }
            }
            composable("details") {
            }
            composable("settings") {

            }

        }
    }
}



