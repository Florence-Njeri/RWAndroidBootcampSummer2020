package com.florencenjeri.cocktailsrecipe

import android.app.Application
import com.florencenjeri.cocktailsrecipe.networking.NewsRepository
import com.florencenjeri.cocktailsrecipe.networking.buildApiService

class App : Application() {
    companion object {

        val apiService by lazy { buildApiService() }
        val newsRepository by lazy { NewsRepository(apiService) }
    }

}