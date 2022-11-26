package com.example.news_app.app.news


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
//import com.example.news_app.app.MainViewModel

//Upgrade here

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsScreen(viewModel: MainViewModel,
                onClicked: () -> Unit
) {
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
            LazyColumn {
                itemsIndexed(items = list){
                        index, item ->
                    Card(onClick = {onClicked.invoke()
                        viewModel.article = list.map { it.toDataModel()}[index]
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 14.dp),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Column {
                            Row(Modifier.padding(top = 10.dp)) {
                                Card(Modifier
                                    .fillMaxWidth(),
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(22.dp)) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = list[index].imageUrl),
                                        modifier = Modifier.size(250.dp),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "Article Image")
                                }
                            }
                            Row(Modifier.padding(top = 10.dp, start = 8.dp)) {
                                Box(Modifier
                                    .fillMaxWidth()) {
                                    Text(text = list[index].title!!, fontSize = 24.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.W400)
                                }
                            }
                            Row(Modifier.padding(horizontal = 8.dp)) {
                                Box(Modifier
                                    .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                                ) {
                                    Column {
                                        Text(text = list[index].author + " • " + list[index].date, fontSize = 16.sp, color = Color.Gray)
                                    }
                                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                                        IconButton(onClick = onClicked) {
                                            Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Favourite Button")
                                        }
                                    }
                                }
                            }

                        }
                    }
                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
}
