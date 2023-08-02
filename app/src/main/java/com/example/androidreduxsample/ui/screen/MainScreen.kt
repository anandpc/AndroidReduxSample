package com.example.androidreduxsample.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.androidreduxsample.data.model.Article
import com.example.androidreduxsample.redux.MainScreenState
import com.example.androidreduxsample.ui.viewmodel.MainViewModel

private const val TAG = "MainScreen"

@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {

    // Collect the latest value from the StateFlow
    // mainScreenState is a StateFlow that emits the current and new state updates of MainScreenState
    val mainScreenState by mainViewModel.mainScreenState.collectAsState()

    // Fetch data when the composable is first displayed
    LaunchedEffect(Unit) {
        mainViewModel.dispatchFetchArticles()
    }

    RenderMainScreen(mainScreenState)
}

/**
 * Render the screen based on the state
 * @param mainScreenState the state of the screen [MainScreenState]
 */
@Composable
private fun RenderMainScreen(mainScreenState: MainScreenState) {
    when {
        mainScreenState.isLoading -> {
            MainScreenLoading()
        }

        mainScreenState.error != null -> {
            MainScreenError(mainScreenState.error)
        }

        else -> {
            ArticleList(mainScreenState.articles)
        }
    }
}

@Composable
fun MainScreenError(error: String?) {
    if (error != null) {
        Text(text = "Error :$error")
    }
}

@Composable
fun MainScreenLoading() =
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }


@Composable
fun ArticleList(articles: List<Article>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(articles) { article ->
            ArticleItem(article) { id ->
                Log.d(TAG, "ArticleList: $id")
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, onArticleClick: (Int) -> Unit) {
    val imagePainter = rememberImagePainter(article.urlToImage)
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.clickable { onArticleClick.invoke(article.id) }) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp),
                maxLines = 1
            )
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.aspectRatio(16f / 9f)
            )
            Text(
                text = article.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    ArticleItem(article = Article(
        id = 1,
        title = "Title",
        description = "Description",
        urlToImage = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png"
    ),
        onArticleClick = { id -> Log.d(TAG, "MainScreenPreview: $id") }
    )
}