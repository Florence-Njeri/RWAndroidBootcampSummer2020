package com.florencenjeri.currentnews

import android.app.Application
import android.content.Context
import androidx.work.*
import com.florencenjeri.currentnews.database.NewsDatabase
import com.florencenjeri.currentnews.database.NewsRepository
import com.florencenjeri.currentnews.network.RemoteApi
import com.florencenjeri.currentnews.network.buildApiService
import com.florencenjeri.currentnews.worker.RefreshDataWorker
import java.util.concurrent.TimeUnit

class App : Application() {


    companion object {
        private lateinit var instance: App
        fun getAppContext(): Context = instance.applicationContext
        val apiService by lazy { buildApiService() }
        val remoteApi by lazy { RemoteApi(apiService) }
        val newsDao by lazy { NewsDatabase.getDatabase(getAppContext()).newsDao() }
        val newsRepository by lazy { NewsRepository(newsDao) }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        hourlyDataSync()
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
