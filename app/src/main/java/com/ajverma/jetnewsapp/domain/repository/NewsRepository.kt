package com.ajverma.jetnewsapp.domain.repository

import com.ajverma.jetnewsapp.data.remote.Article
import com.ajverma.jetnewsapp.data.remote.News
import com.ajverma.jetnewsapp.data.remote.NewsByCategories
import com.ajverma.jetnewsapp.data.remote.NewsBySearch
import com.ajverma.jetnewsapp.data.remote.NewsByTopHeadlines
import com.ajverma.jetnewsapp.data.remote.modelForCategory.CategoryResponse
import com.ajverma.jetnewsapp.domain.util.Resource

interface NewsRepository {
    suspend fun getNewsBySearch(q: String): Resource<News>
    suspend fun getNewsByHeadlines(): Resource<News>
    suspend fun getNewsByCategory(category: String): Resource<CategoryResponse>
}