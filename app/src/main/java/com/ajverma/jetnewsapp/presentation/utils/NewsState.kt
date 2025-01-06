package com.ajverma.jetnewsapp.presentation.utils

data class NewsState<T> (
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)