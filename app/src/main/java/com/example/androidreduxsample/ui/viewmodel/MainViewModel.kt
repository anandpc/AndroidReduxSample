package com.example.androidreduxsample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidreduxsample.data.model.Article
import com.example.androidreduxsample.redux.ArticleListAction
import com.example.androidreduxsample.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val store: Store
) : ViewModel() {

    private val _articles = MutableStateFlow(emptyList<Article>())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            store.appStateFlow.collect {
                _articles.value = it.mainScreenState.articles
                _isLoading.value = it.mainScreenState.isLoading
            }
        }
    }

    suspend fun fetchArticles() = store.dispatch(action = ArticleListAction.Fetch)

}