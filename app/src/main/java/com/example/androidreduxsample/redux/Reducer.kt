package com.example.androidreduxsample.redux

/**
 * Reducer takes old state and action and emits new state if needed.
 */
class Reducer {
    fun reduce(
        oldState: AppState,
        action: Action
    ): AppState {
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

            is ArticleListAction.Failed -> {
                AppState(
                    mainScreenState = oldState.mainScreenState.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = action.error
                    )
                )
            }

            else -> {
                oldState
            }
        }
    }
}

