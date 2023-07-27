package com.example.androidreduxsample.redux

import com.example.androidreduxsample.data.MainRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MiddleWare @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend fun handle(action: Action): Action = withContext(IO) {
        if (action is ArticleListAction.Fetch) {
            val articles = mainRepository.getArticles()
            if (articles.isEmpty().not()) {
                return@withContext ArticleListAction.Loaded(
                    articles = articles
                )
            }
        }

        return@withContext ArticleListAction.Loading
    }
}