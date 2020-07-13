package com.florencenjeri.cocktailsrecipe

import android.app.Application
import android.content.Context
import com.florencenjeri.cocktailsrecipe.model.database.NewsDao
import com.florencenjeri.cocktailsrecipe.model.database.NewsDatabase
import com.florencenjeri.cocktailsrecipe.networking.RemoteApi
import com.florencenjeri.cocktailsrecipe.networking.buildApiService

class App : Application() {
    companion object {
        private lateinit var instance: App

        fun getAppContext(): Context = instance.applicationContext
        val apiService by lazy { buildApiService() }
        val newsRepository by lazy { RemoteApi(apiService) }
       lateinit var newsDao: NewsDao
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        newsDao = NewsDatabase.getDatabase(this).newsDao()

    }

}
