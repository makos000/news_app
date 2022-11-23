package com.example.news_app.app.news

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(viewModel: MainViewModel, onClicked: () -> Unit) {
    Surface {
        Row {
            IconButton(onClick = onClicked) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back button")
            }
        }

    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(40.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = viewModel.article.content!!)
        // Text(text = viewModel.article.content)
    }
}