package com.florencenjeri.currentnews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.currentnews.database.NewsRepository
import com.florencenjeri.currentnews.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun fetchNews():LiveData<List<News>> = newsRepository.fetchNews()

    fun refreshNewsInDb() = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.refreshNews()
    }
}