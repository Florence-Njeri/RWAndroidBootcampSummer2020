package com.florencenjeri.currentnews.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.florencenjeri.currentnews.App
import com.florencenjeri.currentnews.R

class RefreshDataWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
        val app = App.getAppContext()
    }

    override suspend fun doWork(): Result {
        //Sync data to the database
        return try {
            App.newsRepository.insertNews()
            val notificationManager = ContextCompat.getSystemService(
                app,
                NotificationManager::class.java
            ) as NotificationManager
            notificationManager.sendNotification(app.getString(R.string.data_uptodate), app)
            createChannel(
                app.getString(R.string.refresh_data_channel_id),
                app.getString(R.string.refresh_data_channel_name)
            )
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }

    }
    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH)
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = app.getString(R.string.refresh_data_channel_description)

            val notificationManager = App.getAppContext().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }

}