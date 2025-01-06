package com.ajverma.jetnewsapp.data.remote.modelForCategory

data class CategoryResponse(
    val sources: List<Source>,
    val status: String
)

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)