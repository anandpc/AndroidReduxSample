package com.example.androidreduxsample.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.androidreduxsample.ui.state.ArticleUiState
import com.example.androidreduxsample.ui.viewmodel.MainViewModel

private const val TAG = "MainScreen"

@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {

    val articlesUiState by mainViewModel.articles.collectAsState()

    // Fetch data when the composable is first displayed
    LaunchedEffect(Unit) {
        mainViewModel.fetchData()
    }

    ArticleList(articlesUiState = articlesUiState)
}

@Composable
fun ArticleList(articlesUiState: List<ArticleUiState>) {
    LazyColumn {
        items(articlesUiState) { article ->
            ArticleItem(article) { id ->
                Log.d(TAG, "ArticleList: $id")
            }
        }
    }
}

@Composable
fun ArticleItem(articleUiState: ArticleUiState, onArticleClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onArticleClick.invoke(articleUiState.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = articleUiState.title,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Image(
                painter = rememberImagePainter(articleUiState.urlToImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = articleUiState.description,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}