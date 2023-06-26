package com.example.androidreduxsample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidreduxsample.data.MainRepository
import com.example.androidreduxsample.ui.state.ArticleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _articles = MutableStateFlow(emptyList<ArticleUiState>())
    val articles: StateFlow<List<ArticleUiState>> = _articles.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            _articles.value = mainRepository.getArticles()
        }
    }
}