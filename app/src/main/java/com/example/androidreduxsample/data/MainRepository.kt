package com.example.androidreduxsample.data

import android.util.Log
import com.example.androidreduxsample.data.mapper.mapBaseResponseToArticleResponse
import com.example.androidreduxsample.data.model.Article
import com.example.androidreduxsample.data.network.ArticlesApi
import javax.inject.Inject

private const val TAG = "MainRepository"

class MainRepository @Inject constructor(private val api: ArticlesApi) {

    suspend fun getArticles(): List<Article> {
        val response = api.getTopHeadlines()
        return if (response.articles.isEmpty().not()) {
            mapBaseResponseToArticleResponse(
               response
            )
        } else {
            Log.e(TAG, "getArticles: ")
            emptyList()
        }
    }
}