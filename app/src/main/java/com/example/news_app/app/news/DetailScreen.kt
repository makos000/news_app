package com.example.news_app.app.news

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.news_app.R
//import com.example.news_app.app.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(viewModel: MainViewModel, onClicked: () -> Unit) {

    var offset = rememberScrollState()

    Surface (modifier = Modifier.verticalScroll(offset)){
        Column(
            modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberAsyncImagePainter(model = viewModel.article.imageUrl),
                        modifier = Modifier.size(width = maxOf(Dp.Infinity), height = maxOf(300.dp)),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Article Image", alignment = Alignment.Center)
                }
                IconButton(onClick = onClicked, Modifier.padding(10.dp)) {
                    Image(painter = painterResource(id = R.drawable.arrow), contentDescription = "back button",Modifier.size(35.dp))
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = viewModel.article.title, fontSize = 28.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold)
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                Text(text = viewModel.article.author + " • " + viewModel.article.date, fontSize = 16.sp, color = Color.Gray)
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                Text(text = viewModel.article.content, fontSize = 22.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Medium)
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                val context = LocalContext.current
                val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.article.url)) }
                Button(onClick = { context.startActivity(intent) }) {
                    Text(text = "Read More...")
                }
            }

            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Read More...")
                }
            }
        }

    }


}