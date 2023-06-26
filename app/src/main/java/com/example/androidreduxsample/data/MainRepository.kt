package com.example.androidreduxsample.data

import android.util.Log
import com.example.androidreduxsample.data.mapper.mapArticleResponseToArticleUiState
import com.example.androidreduxsample.data.network.ArticlesApi
import com.example.androidreduxsample.ui.state.ArticleUiState
import javax.inject.Inject

private const val TAG = "MainRepository"

class MainRepository @Inject constructor(private val api: ArticlesApi) {

    suspend fun getArticles(): List<ArticleUiState> {
        val response = api.getTopHeadlines()
        return if (response.articles.isEmpty().not()) {
            mapArticleResponseToArticleUiState(
               response
            )
        } else {
            Log.e(TAG, "getArticles: ")
            emptyList()
        }
    }
}