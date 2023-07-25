package com.example.androidreduxsample.redux

import com.example.androidreduxsample.data.model.Article

interface Action

sealed class ArticleListAction : Action {
    object Fetch : Action
    object Loading : Action
    data class Loaded(val articles: List<Article>) : Action
}