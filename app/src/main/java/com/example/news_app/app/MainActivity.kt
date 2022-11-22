package com.example.news_app.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.news_app.app.theme.News_appTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.news_app.app.navbar.BottomNavItem
import com.example.news_app.app.navbar.BottomNavigationBar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            News_appTheme {

                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Details",
                                    route = "details",
                                    icon = Icons.Default.Email,
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings,
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ){
                    Navigation(navController = navController)
                }
            }
        }
    }

//    @SuppressLint("UnrememberedMutableState")
//    @Composable
//    fun NewsApp() {
//        var showNewsScreen by rememberSaveable { mutableStateOf(viewModel.newsScreen) }
//
//        if(showNewsScreen){
//            NewsScreen(viewModel = viewModel, onClicked = {viewModel.newsScreen = false
//            showNewsScreen = viewModel.newsScreen})
//        }
//        else{
//            DetailScreen(viewModel = viewModel, onClicked = {viewModel.newsScreen = true
//            showNewsScreen = viewModel.newsScreen})
//        }
//    }




    @Composable
    fun Navigation(navController: NavHostController){

        var showNewsScreen by rememberSaveable { mutableStateOf(viewModel.newsScreen) }

        NavHost(navController = navController, startDestination = "home" ){
            composable("home") {
                if(showNewsScreen){
                    NewsScreen(viewModel = viewModel, onClicked = {viewModel.newsScreen = false
                        showNewsScreen = viewModel.newsScreen})
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



