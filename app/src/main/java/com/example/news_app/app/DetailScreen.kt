package com.example.news_app.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Upgrade here

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