package com.example.news_app.app

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.news_app.App
import com.example.news_app.app.authentication.common.snackbar.SnackbarManager
import com.example.news_app.app.authentication.login.LoginScreen
import com.example.news_app.app.authentication.sign_up.SignUpScreen
import com.example.news_app.app.navbar.BottomNavItem
import com.example.news_app.app.navbar.BottomNavigationBar
import com.example.news_app.app.navbar.LOGIN_SCREEN
import com.example.news_app.app.navbar.SIGN_UP_SCREEN
import com.example.news_app.app.news.DetailScreen
import com.example.news_app.app.news.MainViewModel
import com.example.news_app.app.news.NewsScreen
import com.example.news_app.app.theme.News_appTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()


    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            News_appTheme {
                App()
            }
        }
    }
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun App(){
        val appState = rememberAppState()

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

            },
            snackbarHost= {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.padding(8.dp),
                    snackbar = { snackbarData ->
                        Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                    }
                )

            },
            scaffoldState =appState.scaffoldState

        ){
            Navigation(navController = navController)
        }
    }
    @Composable
    fun rememberAppState(
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        snackbarManager: SnackbarManager = SnackbarManager,
        resources: Resources = resources(),
        coroutineScope: CoroutineScope = rememberCoroutineScope()
    ) =
        remember(scaffoldState, snackbarManager, resources, coroutineScope) {
            AppState(scaffoldState, snackbarManager, resources, coroutineScope)
        }

    @Composable
    @ReadOnlyComposable
    fun resources(): Resources {
        LocalConfiguration.current
        return LocalContext.current.resources
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
            composable("details") {
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


