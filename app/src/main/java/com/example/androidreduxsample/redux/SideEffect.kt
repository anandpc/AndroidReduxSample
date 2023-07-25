package com.example.androidreduxsample.redux

import android.util.Log

class SideEffect {

    fun effect(action: Action): Action {
        if (action is ArticleListAction.Fetch) {
            Log.d(TAG, "startSideEffect: $action")
            // how to call api from here
        }
        return ArticleListAction.Loading
    }

    companion object {
        private const val TAG = "SideEffects"
    }
}