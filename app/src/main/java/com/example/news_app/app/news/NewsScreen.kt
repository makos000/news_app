package com.example.news_app.app.news

import android.media.tv.TvContract.Programs.Genres.NEWS
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news_app.R
import com.example.news_app.app.authentication.common.composable.ActionToolbar
import com.example.news_app.app.authentication.common.composable.BasicToolbar
import com.example.news_app.app.authentication.common.ext.smallSpacer
import com.example.news_app.app.authentication.common.ext.toolbarActions


@OptIn(ExperimentalMaterialApi::class)
@Composable

fun NewsScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onClicked: () -> Unit,
    openScreen: (String) -> Unit
) {
    LaunchedEffect(key1 = true){
        viewModel.getData("science")
    }

    val list = viewModel.data.collectAsState().value.data
Column() {

    BasicToolbar(R.string.news)

    Spacer(modifier = Modifier.smallSpacer())

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

}


