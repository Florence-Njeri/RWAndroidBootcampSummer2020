package com.florencenjeri.cocktailsrecipe.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.florencenjeri.cocktailsrecipe.App

class RefreshDataWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        //Sync data to the database
        return try {
            App.newsRepository.insertNews()
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }

    }

}