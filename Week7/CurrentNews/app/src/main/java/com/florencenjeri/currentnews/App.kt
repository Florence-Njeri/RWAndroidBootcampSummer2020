package com.florencenjeri.currentnews

import android.app.Application
import android.content.Context
import androidx.work.*
import com.florencenjeri.currentnews.di.*
import com.florencenjeri.currentnews.worker.RefreshDataWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class App : Application() {


    companion object {
        private lateinit var instance: App
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        //Initialize Koin
        startKoin {
            //For logging Koin related errors
            androidLogger()
            //Declare my app context
            androidContext(this@App)
            //Declare all my D.I modules
            modules(
                listOf(
                    appModule, networkModule, databaseModule, presentationModule,
                    repositoryModule
                )
            )
        }
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
