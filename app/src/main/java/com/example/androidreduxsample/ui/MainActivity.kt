package com.example.androidreduxsample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidreduxsample.ui.screen.MainScreen
import com.example.androidreduxsample.ui.theme.AndroidReduxSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidReduxSampleTheme {
                MainScreen()
            }
        }
    }
}