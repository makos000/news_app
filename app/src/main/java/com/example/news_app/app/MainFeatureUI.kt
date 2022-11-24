package com.example.news_app.app

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.news_app.app.news.DetailScreen
import com.example.news_app.app.news.MainViewModel
import com.example.news_app.app.news.NewsScreen
import com.example.news_app.app.authentication.common.snackbar.SnackbarManager
import com.example.news_app.app.authentication.login.LoginScreen
import com.example.news_app.app.authentication.settings.SettingsScreen
import com.example.news_app.app.authentication.sign_up.SignUpScreen
import com.example.news_app.app.authentication.splash.SplashScreen
import com.example.news_app.app.navbar.BottomNavItem
import com.example.news_app.app.navbar.BottomNavigationBar
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterialApi
fun NewsApp() {
    val appState = rememberAppState3()
    val navController=appState.navController

    Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        }
                    )
                },
                scaffoldState = appState.scaffoldState,
                bottomBar= {
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

            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)

                ) {
                    navGraph(appState)
                }
            }
}

@Composable
fun rememberAppState3(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        AppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterialApi
fun NavGraphBuilder.navGraph(appState: AppState) {

    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
/*
    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }
*/
    composable(LOGIN_SCREEN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(NEWS_SCREEN) {
        val   mainViewModel: MainViewModel = hiltViewModel()
        var showNewsScreen by rememberSaveable { mutableStateOf( mainViewModel.newsScreen) }

        if(showNewsScreen){
               NewsScreen(viewModel = mainViewModel,
                   onClicked = { mainViewModel.newsScreen = false
                       showNewsScreen =  mainViewModel.newsScreen},
                   openScreen = { route -> appState.navigate(route)}
               )
           }
           else{
               DetailScreen(viewModel =  mainViewModel, onClicked = { mainViewModel.newsScreen = true
                   showNewsScreen =  mainViewModel.newsScreen})
           }}

    composable("details") {

    }
    composable(SETTINGS_SCREEN) {

        SettingsScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }
}