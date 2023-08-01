package com.example.androidreduxsample.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class Store @Inject constructor(
    private val reducer: Reducer,
    private val middleWare: MiddleWare
) {

    private val _appStateFlow = MutableStateFlow(
        AppState(
            mainScreenState = MainScreenState(
                articles = emptyList(),
                isLoading = false
            )
        )
    )

    val appStateFlow: StateFlow<AppState> = _appStateFlow.asStateFlow()

    suspend fun dispatch(action: Action) {
        middleWare.handle(action)
            .collect { newAction ->
                val newState = reducer.reduce(_appStateFlow.value, newAction)

                _appStateFlow.value = newState
        }
    }
}
