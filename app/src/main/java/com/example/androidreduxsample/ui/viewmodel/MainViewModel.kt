package com.example.androidreduxsample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidreduxsample.data.model.Article
import com.example.androidreduxsample.redux.ArticleListAction
import com.example.androidreduxsample.redux.MainScreenState
import com.example.androidreduxsample.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  MainViewModel is responsible for handling user interactions and updating the UI accordingly.
 *  ViewModel for [MainActivity]
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    val store: Store,
    private val dispatchers: Dispatchers
) : ViewModel() {

    /**
     * mainScreenState is a [StateFlow] that emits the current and new state updates of [MainScreenState]
     * [StateFlow] is a state-holder observable flow that emits the current and new state updates
     */
    val mainScreenState: StateFlow<MainScreenState> = store.appStateFlow.map { appState ->
        appState.mainScreenState
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = MainScreenState(
            articles = emptyList(),
            isLoading = false,
            error = null
        )
    )

    /**
     * dispatchFetchArticles is a suspend function that dispatches [ArticleListAction.Fetch] action
     */
    suspend fun dispatchFetchArticles() = withContext(dispatchers.IO) {
        store.dispatch(action = ArticleListAction.Fetch)
    }
}
