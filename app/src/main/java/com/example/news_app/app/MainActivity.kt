package com.example.news_app.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.news_app.app.authentication.login.LoginScreen
import com.example.news_app.app.authentication.sign_up.SignUpScreen
import com.example.news_app.app.navbar.BottomNavItem
import com.example.news_app.app.navbar.BottomNavigationBar
import com.example.news_app.app.navbar.LOGIN_SCREEN
import com.example.news_app.app.navbar.SIGN_UP_SCREEN
import com.example.news_app.app.news.DetailScreen
import com.example.news_app.app.news.MainViewModel
import com.example.news_app.app.news.NewsScreen
import com.example.news_app.app.preferences.PreferencesScreen
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
                                    name = "Preferences",
                                    route = "preferences",
                                    icon = Icons.Default.Settings,
                                ),
                                BottomNavItem(
                                    name = "SignIn ",
                                    route = "login",
                                    icon = Icons.Default.AccountBox,
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

    @Composable
    fun Navigation(navController: NavHostController) {

        var showNewsScreen by rememberSaveable { mutableStateOf(viewModel.newsScreen) }

        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                if (showNewsScreen) {
                    NewsScreen(viewModel = viewModel, onClicked = {
                        viewModel.newsScreen = false
                        showNewsScreen = viewModel.newsScreen
                    })
                } else {
                    DetailScreen(viewModel = viewModel, onClicked = {
                        viewModel.newsScreen = true
                        showNewsScreen = viewModel.newsScreen
                    })
                }
            }
            composable("preferences") {
                PreferencesScreen(openAndPopUp = { route, popUp ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        popUpTo(popUp) { inclusive = true }
                    } })
            }
            composable(route = LOGIN_SCREEN) {
                LoginScreen(openAndPopUp = { route, popUp ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        popUpTo(popUp) { inclusive = true }
                    } })
            }
            composable(SIGN_UP_SCREEN) {
                SignUpScreen(openAndPopUp = { route, popUp ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        popUpTo(popUp) { inclusive = true }
                    } })
            }

        }
    }
}


