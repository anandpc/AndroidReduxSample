package com.example.androidreduxsample.di

import com.example.androidreduxsample.data.MainRepository
import com.example.androidreduxsample.redux.Reducer
import com.example.androidreduxsample.redux.Middleware
import com.example.androidreduxsample.redux.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ReduxModule {

    @Singleton
    @Provides
    fun providesReducer() = Reducer()

    @Singleton
    @Provides
    fun providesMiddleWare(repository: MainRepository) = Middleware(repository)

    @Singleton
    @Provides
    fun providesStore(
        reducer: Reducer,
        middleWare: Middleware
    ) = Store(
        reducer = reducer,
        middleware = middleWare
    )
}