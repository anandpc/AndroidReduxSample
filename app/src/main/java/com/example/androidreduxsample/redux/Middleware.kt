package com.example.androidreduxsample.redux

import com.example.androidreduxsample.data.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Middleware @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend fun handle(action: Action): Flow<Action> = flow {
        when (action) {
            is ArticleListAction.Fetch -> {
                // emit loading action
                emit(ArticleListAction.Loading)
                // fetch articles from network
                val articles = mainRepository.getArticles()
                // emit loaded action with articles
                emit(ArticleListAction.Loaded(articles = articles))
            }
            // return action as is if no match
            else -> emit(action)
        }
    }.catch {
        emit(ArticleListAction.Failed(it.message))
    }
}