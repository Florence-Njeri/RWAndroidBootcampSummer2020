package com.florencenjeri.currentnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.currentnews.database.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsViewModel : ViewModel() ,KoinComponent{
    val newsRepository: NewsRepository by inject()
    fun fetchNews() = newsRepository.fetchNews()

    fun refreshNewsInDb() = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.refreshNews()
    }
}