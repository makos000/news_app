package com.example.news_app.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
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
fun NewsScreen(viewModel: MainViewModel, onClicked: () -> Unit) {
    LaunchedEffect(key1 = true){
        viewModel.getData("science")
    }

    val list = viewModel.data.collectAsState().value.data

    if (list != null){
        if (list.isEmpty()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "There is nothing to display...")
            }
        }else{
            LazyColumn(){
                itemsIndexed(items = list){
                    index, item ->
                    Card(onClick = {onClicked.invoke()

                        viewModel.article = list.map { it.toDataModel()}[index]
                       // viewModel.article = list.map { it.toData()}[index]
                        //viewModel.article = item.newsModel.data[index]
                    },
                        modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        elevation = 8.dp,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = list[index].title!!, fontSize = 25.sp)
                           // Text(text = item.newsModel.data[index].title, fontSize = 25.sp)
                        }
                    }
                }
            }
        }
    }
}