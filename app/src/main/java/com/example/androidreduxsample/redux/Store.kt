package com.example.androidreduxsample.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Store(
    private val reducer: Reducer = Reducer(),
    private val sideEffect: SideEffect = SideEffect()
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

    fun dispatch(action: Action) {
        val newAction = sideEffect.effect(action)
        val newState = reducer.reduce(_appStateFlow.value, newAction)
        _appStateFlow.value = newState
    }
}

