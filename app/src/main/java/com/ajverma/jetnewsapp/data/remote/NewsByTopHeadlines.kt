package com.ajverma.jetnewsapp.data.remote

import com.ajverma.jetnewsapp.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsByTopHeadlines {
    @GET("v2/top-headlines")
    suspend fun getNewsByTopHeadlines(
        @Query("country") country: String = "us",
        @Query("sort") sort: String = "popularity",
        @Query("apikey") api: String = Constants.API_KEY
    ): News
}
