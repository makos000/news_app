package com.example.news_app.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.app.authentication.common.snackbar.SnackbarManager
import com.example.news_app.app.authentication.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class AppViewModel() : ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            block = block
        )
}