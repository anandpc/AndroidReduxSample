package com.example.androidreduxsample.network

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") countryCode: String = "in",
        @Query("apiKey") apiKey: String = "f40740e5a3784961861353e9fdcb17dc"
    ): Response

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}