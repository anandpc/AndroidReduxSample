package com.example.androidreduxsample.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen() {
    UsersList()
}

val data: List<String> = listOf("1","2", "3")

@Composable
fun UsersList() {
    LazyColumn {
        items(data) { item ->
            Text(text = item)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}