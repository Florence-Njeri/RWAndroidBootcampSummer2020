package com.florencenjeri.cocktailsrecipe

import android.app.Application
import android.content.Context
import androidx.work.*
import com.florencenjeri.cocktailsrecipe.database.NewsDao
import com.florencenjeri.cocktailsrecipe.database.NewsDatabase
import com.florencenjeri.cocktailsrecipe.database.NewsRepository
import com.florencenjeri.cocktailsrecipe.network.RemoteApi
import com.florencenjeri.cocktailsrecipe.network.buildApiService
import com.florencenjeri.cocktailsrecipe.worker.RefreshDataWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

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
        GlobalScope.launch(Dispatchers.Default) {
            hourlyDataSync()
        }
    }

    //Background work should not delay app start
    private fun hourlyDataSync() {
        val constraints = Constraints.Builder()
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val hourlyDataSyncRequest =
            PeriodicWorkRequestBuilder<RefreshDataWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            hourlyDataSyncRequest
        )
    }
}
