package com.florencenjeri.cocktailsrecipe.worker

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.florencenjeri.cocktailsrecipe.R

private val NOTIFICATION_ID = 0
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.data_sysnced)
    )
        .setSmallIcon(R.drawable.ic_baseline_new_releases_24)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
    notify(NOTIFICATION_ID, builder.build())
}