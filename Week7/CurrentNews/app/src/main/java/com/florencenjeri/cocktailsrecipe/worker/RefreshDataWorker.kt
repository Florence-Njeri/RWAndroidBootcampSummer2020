package com.florencenjeri.cocktailsrecipe.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.florencenjeri.cocktailsrecipe.App

class RefreshDataWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        //Sync data to the database

        App.newsRepository.insertNews()
        return Result.success()
    }

}