package com.florencenjeri.cocktailsrecipe.model.database

import androidx.lifecycle.LiveData
import com.florencenjeri.cocktailsrecipe.App
import com.florencenjeri.cocktailsrecipe.model.New
import com.florencenjeri.cocktailsrecipe.model.Success

class NewsRepository() {
    fun fetchNews(): LiveData<List<New>> = App.newsDao.fetchNews()

    suspend fun insertNews() {
        val result = App.newsRepository.fetchNews()
        if (result is Success) {
            return App.newsDao.insertNews(result.data)
        }

    }
}