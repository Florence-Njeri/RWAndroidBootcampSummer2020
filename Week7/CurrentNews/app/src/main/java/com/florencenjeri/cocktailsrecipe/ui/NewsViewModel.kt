package com.florencenjeri.cocktailsrecipe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.cocktailsrecipe.model.New
import com.florencenjeri.cocktailsrecipe.model.database.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun fetchNews(): LiveData<List<New>> {
        return newsRepository.fetchNews()
    }

    fun insertNewsToDb() = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.insertNews()
    }
}