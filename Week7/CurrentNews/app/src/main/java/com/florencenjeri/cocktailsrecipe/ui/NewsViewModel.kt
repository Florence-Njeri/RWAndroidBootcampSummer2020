package com.florencenjeri.cocktailsrecipe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.florencenjeri.cocktailsrecipe.database.NewsRepository
import com.florencenjeri.cocktailsrecipe.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun fetchNews(): LiveData<PagedList<News>> {
        return newsRepository.fetchNews()
    }

    fun insertNewsToDb() = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.insertNews()
    }
}