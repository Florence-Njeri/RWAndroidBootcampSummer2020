package com.florencenjeri.cocktailsrecipe.database

import androidx.lifecycle.LiveData
import com.florencenjeri.cocktailsrecipe.App
import com.florencenjeri.cocktailsrecipe.model.News
import com.florencenjeri.cocktailsrecipe.model.Success

class NewsRepository() {
    fun fetchNews(): LiveData<List<News>> = App.newsDao.fetchNews()

    suspend fun insertNews() {
        //Refresh the database data once a user is connected to the internet
        val result = App.remoteApi.fetchNews()
        if (result is Success) {
            return App.newsDao.insertNews(result.data)
        }

    }
}