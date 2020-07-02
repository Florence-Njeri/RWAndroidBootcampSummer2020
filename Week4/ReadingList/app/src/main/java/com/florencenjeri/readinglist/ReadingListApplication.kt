package com.florencenjeri.readinglist

import android.app.Application
import android.content.Context

class ReadingListApplication : Application() {

    companion object {
        private lateinit var instance: ReadingListApplication

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this

        super.onCreate()
    }
}