package com.example.androidreduxsample.redux

import com.example.androidreduxsample.data.model.Article

data class AppState(
    val mainScreenState: MainScreenState
)

data class MainScreenState(
    val articles: List<Article>,
    var isLoading: Boolean
)
