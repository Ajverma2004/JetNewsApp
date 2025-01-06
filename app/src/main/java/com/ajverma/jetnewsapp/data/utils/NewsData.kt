package com.ajverma.jetnewsapp.data.utils

import com.ajverma.jetnewsapp.data.remote.Article
import com.ajverma.jetnewsapp.data.remote.modelForCategory.Source

sealed class NewsData {
    data class Articles(val articles: List<Article>) : NewsData()
    data class Sources(val sources: List<Source>) : NewsData()
}