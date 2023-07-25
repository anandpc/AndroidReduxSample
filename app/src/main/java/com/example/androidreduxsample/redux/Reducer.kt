package com.example.androidreduxsample.redux

import com.example.androidreduxsample.data.model.Article

class Reducer {
    fun reduce(oldState: AppState, action: Action): AppState {
        return when (action) {
            is ArticleListAction.Fetch -> {
                AppState(
                    mainScreenState = oldState.mainScreenState.copy(
                        articles = mockArticles,
                        isLoading = false
                    )
                )
            }
            is ArticleListAction.Loading -> {
                AppState(
                    mainScreenState = oldState.mainScreenState.copy(
                        articles = oldState.mainScreenState.articles,
                        isLoading = true
                    )
                )
            }
            else -> {
                oldState
            }
        }
    }

    val mockArticles = listOf(
        Article(
            id = 0,
            title = "title 0",
            description = "description 0",
            urlToImage = "url 0"
        ),
        Article(
            id = 1,
            title = "title 1",
            description = "description 1",
            urlToImage = "url 1"
        ),
        Article(
            id = 2,
            title = "title 2",
            description = "description 2",
            urlToImage = "url 2"
        ),
    )
}

