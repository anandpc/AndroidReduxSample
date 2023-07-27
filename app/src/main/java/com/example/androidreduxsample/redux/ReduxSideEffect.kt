package com.example.androidreduxsample.redux

import com.example.androidreduxsample.data.MainRepository
import javax.inject.Inject

class ReduxSideEffect @Inject constructor(private val mainRepository: MainRepository) {

    suspend fun effect(action: Action): Action {
        if (action is ArticleListAction.Fetch) {
            val articles = mainRepository.getArticles()
            if (articles.isEmpty().not()) {
                return ArticleListAction.Loaded(
                    articles = articles
                )
            }
        }
        return ArticleListAction.Loading
    }
}