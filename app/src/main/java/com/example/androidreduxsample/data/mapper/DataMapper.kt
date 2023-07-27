package com.example.androidreduxsample.data.mapper

import com.example.androidreduxsample.data.model.Article
import com.example.androidreduxsample.data.network.model.response.BaseResponse

fun mapBaseResponseToArticleResponse(response: BaseResponse): List<Article> {
    return response.articles.map {
        Article(
            id = it.id,
            title = it.title ?: "No title",
            description = it.description ?: "No Description",
            urlToImage = it.urlToImage ?: "No Image"
        )
    }
}