package com.ajverma.jetnewsapp.data.repositoryImpl

import android.util.Log
import com.ajverma.jetnewsapp.data.remote.Article
import com.ajverma.jetnewsapp.data.utils.Constants
import com.ajverma.jetnewsapp.data.remote.News
import com.ajverma.jetnewsapp.data.remote.NewsByCategories
import com.ajverma.jetnewsapp.data.remote.NewsBySearch
import com.ajverma.jetnewsapp.data.remote.NewsByTopHeadlines
import com.ajverma.jetnewsapp.data.remote.modelForCategory.CategoryResponse
import com.ajverma.jetnewsapp.domain.repository.NewsRepository
import com.ajverma.jetnewsapp.domain.util.Resource
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val searchApi: NewsBySearch,
    private val headlinesApi: NewsByTopHeadlines,
    private val categoryApi: NewsByCategories
): NewsRepository {
    override suspend fun getNewsBySearch(q: String): Resource<News> {
        return try {
            Resource.Success(
                data = searchApi.getNewsBySearch(q)
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getNewsByHeadlines(): Resource<News> {
        return try {
            Log.d("API_REQUEST", "Fetching top headlines from API.")
            Log.d("API_REQUEST", "Endpoint: https://newsapi.org/v2/top-headlines")
            Log.d("API_REQUEST", "Parameters: apiKey=${Constants.API_KEY.take(5)}****")

            // Make the API request
            val response = headlinesApi.getNewsByTopHeadlines()

            Resource.Success(data = response)
        } catch (e: HttpException) {
            // Log HTTP errors
            val errorBody = e.response()?.errorBody()?.string()
            Log.e("API_ERROR", "HTTP Error: Code=${e.code()}, Message=${e.message()}")
            Log.e("API_ERROR", "Error Body: $errorBody")
            Resource.Error(errorBody ?: e.message())
        } catch (e: Exception) {
            // Log general exceptions
            Log.e("API_ERROR", "General Exception: ${e.message}")
            Resource.Error(e.message ?: "Unknown error occurred")
        }
    }



    override suspend fun getNewsByCategory(category: String): Resource<CategoryResponse> {
        return try {
            Resource.Success(
                data = categoryApi.getNewsByCategory(category)
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }
}