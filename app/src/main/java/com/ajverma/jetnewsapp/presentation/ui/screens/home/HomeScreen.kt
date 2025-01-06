package com.ajverma.jetnewsapp.presentation.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.ajverma.jetnewsapp.domain.util.CustomTextField
import com.ajverma.jetnewsapp.presentation.ui.screens.News.NewsScreen
import com.ajverma.jetnewsapp.presentation.utils.NewsItem
import com.ajverma.jetnewsapp.presentation.utils.NewsState
import com.ajverma.jetnewsapp.data.utils.NewsData
import com.ajverma.jetnewsapp.presentation.utils.SourceItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navController: NavController
) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        if (state.data == null && !state.isLoading) {
            viewModel.getNewsByHeadline()
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        var text by remember { mutableStateOf("") }

        Column(
            Modifier.fillMaxWidth()
        ) {

            CustomTextField(
                text = text,
                onTextChange = { text = it },
                placeholder = "Search here...",
                keyboardType = KeyboardType.Text,
                trailingIcon = Icons.Default.Search,
                onTrailingIconClick = {
                    viewModel.getNewsBySearch(text)
                },
                focusedBackgroundColor = Color.Transparent,
                unfocusedBackgroundColor = Color.White,
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
                textColor = Color.Black
            )

            when (val data = state.data) {
                is NewsData.Articles -> {
                    LazyColumn(
                        Modifier.fillMaxSize()
                    ) {
                        items(data.articles) { article ->
                            NewsItem(
                                article = article,
                                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                                onClick = {
                                    navController.navigate("News?url=${article.url}")
                                }
                            )
                        }
                    }
                }
                is NewsData.Sources -> {
                    LazyColumn(
                        Modifier.fillMaxSize()
                    ) {
                        items(data.sources) { source ->
                            SourceItem(
                                source = source,
                                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                                onClick = {
                                    navController.navigate("News?url=${source.url}")
                                }
                            )
                        }
                    }
                }
                null -> {
                    // Handle null state
                }
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error?.let { error ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Oops! Something went wrong: $error")
                    Button(onClick = { viewModel.getNewsByHeadline() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}