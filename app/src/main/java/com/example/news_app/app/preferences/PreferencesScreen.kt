package com.example.news_app.app.preferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news_app.app.authentication.login.LoginViewModel
import com.example.news_app.app.news.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PreferencesScreen(openAndPopUp: (String, String) -> Unit, viewModel: MainViewModel) {
    Text(text = "preferences")
}