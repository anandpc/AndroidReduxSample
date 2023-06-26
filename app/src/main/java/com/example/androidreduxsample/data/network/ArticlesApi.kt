package com.example.androidreduxsample.data.network

import com.example.androidreduxsample.data.network.model.response.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") countryCode: String = "in",
        @Query("apiKey") apiKey: String = "f40740e5a3784961861353e9fdcb17dc"
    ): BaseResponse
}