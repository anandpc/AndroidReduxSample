package com.example.androidreduxsample.network.model.response

import com.example.androidreduxsample.network.model.Article

data class BaseResponse(
    val articles: List<Article>, val status: String, val totalResults: Int
)