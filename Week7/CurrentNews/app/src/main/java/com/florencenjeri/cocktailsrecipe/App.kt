package com.florencenjeri.cocktailsrecipe

import android.app.Application
import android.content.Context
import com.florencenjeri.cocktailsrecipe.database.NewsDao
import com.florencenjeri.cocktailsrecipe.database.NewsDatabase
import com.florencenjeri.cocktailsrecipe.database.NewsRepository
import com.florencenjeri.cocktailsrecipe.network.RemoteApi
import com.florencenjeri.cocktailsrecipe.network.buildApiService

class App : Application() {
    companion object {
        private lateinit var instance: App

        fun getAppContext(): Context = instance.applicationContext
        val apiService by lazy { buildApiService() }
        val remoteApi by lazy { RemoteApi(apiService) }
       lateinit var newsDao: NewsDao
        val newsRepository by lazy { NewsRepository() }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        newsDao = NewsDatabase.getDatabase(this).newsDao()

    }

}
