package com.example.news_app.app.preferences

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.news_app.app.news.MainViewModel
import com.example.news_app.app.preferences.composable.PrefCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PreferencesScreen(openAndPopUp: (String, String) -> Unit, viewModel: MainViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
        Row(modifier = Modifier.padding(15.dp)) {
            Text(text = "Choose your favourite topics",
                fontSize = 40.sp,
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(modifier = Modifier.padding(10.dp)) {
            PrefCard(category = "business", onClicked = {viewModel.categories.add("business")})
            PrefCard(category = "sports", onClicked = {viewModel.categories.add("sports")})
            PrefCard(category = "world", onClicked = {viewModel.categories.add("world")})
        }
        Row(modifier = Modifier.padding(10.dp)) {
            PrefCard(category = "politics", onClicked = {viewModel.categories.add("politics")})
            PrefCard(category = "technology", onClicked = {viewModel.categories.add("technology")})
            PrefCard(category = "startup", onClicked = {viewModel.categories.add("startup")})
        }
        Row(modifier = Modifier.padding(10.dp)) {
            PrefCard(category = "entertainment", onClicked = {viewModel.categories.add("entertainment")})
            PrefCard(category = "miscellaneous", onClicked = {viewModel.categories.add("miscellaneous")})
        }
        Row(modifier = Modifier.padding(10.dp)) {

            PrefCard(category = "science", onClicked = {viewModel.categories.add("science")})
            PrefCard(category = "automobile", onClicked = {viewModel.categories.add("automobile")})
        }
    }
}