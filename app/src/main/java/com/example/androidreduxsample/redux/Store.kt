package com.example.androidreduxsample.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class Store @Inject constructor(
    private val reducer: Reducer,
    private val middleware: Middleware
) {

    private val _appStateFlow = MutableStateFlow(
        AppState(
            mainScreenState = MainScreenState(
                articles = emptyList(),
                isLoading = false,
                error = null
            )
        )
    )

    val appStateFlow: StateFlow<AppState> = _appStateFlow.asStateFlow()

    suspend fun dispatch(action: Action) {
        middleware.handle(action)
            .collect { newAction ->
                // update app state
                val newState = reducer.reduce(_appStateFlow.value, newAction)
                // notify app state observers for value change
                _appStateFlow.value = newState
            }
    }
}
