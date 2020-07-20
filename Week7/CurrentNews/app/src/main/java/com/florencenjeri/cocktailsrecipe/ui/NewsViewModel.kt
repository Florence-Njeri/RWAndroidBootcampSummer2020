package com.florencenjeri.cocktailsrecipe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.cocktailsrecipe.database.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun fetchNews() = newsRepository.fetchNews()


    fun insertNewsToDb() = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.insertNews()
    }
}