package com.ajverma.jetnewsapp.presentation.ui.screens.home


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajverma.jetnewsapp.data.remote.News
import com.ajverma.jetnewsapp.data.utils.NewsData
import com.ajverma.jetnewsapp.domain.repository.NewsRepository
import com.ajverma.jetnewsapp.domain.util.Resource
import com.ajverma.jetnewsapp.presentation.utils.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: NewsRepository
) : ViewModel() {
    var state by mutableStateOf(NewsState<NewsData>())
        private set

    init {
        Log.d("HomeViewModel", "Initializing ViewModel")
        getNewsByHeadline()
    }

    fun getNewsByHeadline(){
        if (state.isLoading) return // Prevent repeated calls
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                data = null,
                error = null
            )

            when(val result = repo.getNewsByHeadlines()){
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        data = result.data?.let { NewsData.Articles(it.articles) },
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        data = null,
                        error = result.message
                    )
                }
            }
        }
    }

    fun getNewsBySearch(q: String){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                data = null,
                error = null
            )

            when(val result = repo.getNewsBySearch(q)){
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        data = result.data?.articles?.let { NewsData.Articles(it) },
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        data = null,
                        error = result.message
                    )
                }
            }
        }
    }

    fun getNewsByCategory(category: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                data = null,
                error = null
            )

            when(val result = repo.getNewsByCategory(category)) {
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        data = result.data?.sources?.let { NewsData.Sources(it) },
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        data = null,
                        error = result.message
                    )
                }
            }
        }
    }
}