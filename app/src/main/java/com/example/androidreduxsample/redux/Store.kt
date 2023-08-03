package com.example.androidreduxsample.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class Store @Inject constructor(
    private val reducer: Reducer,
    private val middleware: Middleware,
    private val dispatchers: Dispatchers
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

    val appStateFlow: StateFlow<AppState>
        get() = _appStateFlow.asStateFlow()

    suspend fun dispatch(action: Action) {
        middleware.handle(action)
        .flowOn(dispatchers.IO)
        .onEach { newAction ->
            val newState = reducer.reduce(_appStateFlow.value, newAction)
            _appStateFlow.value = newState
        }.collect()
    }
}
