package com.example.news_app.app.preferences.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PrefCard(category: String, onClicked: () -> Unit) {

    var clicked by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.padding(5.dp)){
        if (clicked){
            Card(elevation = 0.dp, shape = RoundedCornerShape(15.dp),
                modifier = Modifier.height(35.dp),
                onClick = {clicked = false
                    onClicked.invoke()},
                contentColor = Color.White,
                backgroundColor = Color.Red,
                border = BorderStroke(2.dp, Color.Red),
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(0.dp)) {
                    Text(
                        text = "   $category   ",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
        else{
            Card(elevation = 0.dp, shape = RoundedCornerShape(15.dp),
                modifier = Modifier.height(35.dp),
                onClick = {clicked = true
                    onClicked.invoke()},
                contentColor = Color.Red,
                backgroundColor = Color.White,
                border = BorderStroke(2.dp, Color.Red),
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(0.dp)) {
                    Text(
                        text = "   $category   ",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }


}