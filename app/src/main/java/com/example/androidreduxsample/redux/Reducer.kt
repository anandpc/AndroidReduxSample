package com.example.androidreduxsample.redux

class Reducer {
    fun reduce(oldState: AppState, action: Action): AppState {
        return when (action) {
            is ArticleListAction.Loading -> {
                AppState(
                    mainScreenState = oldState.mainScreenState.copy(
                        articles = oldState.mainScreenState.articles,
                        isLoading = true
                    )
                )
            }

            is ArticleListAction.Loaded -> {
                AppState(
                    mainScreenState = oldState.mainScreenState.copy(
                        articles = action.articles,
                        isLoading = false
                    )
                )
            }

            else -> {
                oldState
            }
        }
    }
}

