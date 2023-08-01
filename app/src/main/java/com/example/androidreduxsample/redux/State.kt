package com.example.androidreduxsample.redux

import com.example.androidreduxsample.data.model.Article

interface State

data class AppState(
    val mainScreenState: MainScreenState
) : State

data class MainScreenState(
    val articles: List<Article>,
    val isLoading: Boolean,
    val error: String?
) : State
