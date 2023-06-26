package com.example.androidreduxsample.data.network.model.response

data class BaseResponse(
    val articles: List<ResponseArticle>, val status: String, val totalResults: Int
)