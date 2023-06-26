package com.example.androidreduxsample.data.mapper

import com.example.androidreduxsample.data.network.model.response.BaseResponse
import com.example.androidreduxsample.ui.state.ArticleUiState

fun mapArticleResponseToArticleUiState(response: BaseResponse): List<ArticleUiState> {
    return response.articles.map {
        ArticleUiState(
            id = it.id,
            title = it.title ?: "No title",
            description = it.description ?: "No Description",
            urlToImage = it.urlToImage ?: "No Image"
        )
    }
}