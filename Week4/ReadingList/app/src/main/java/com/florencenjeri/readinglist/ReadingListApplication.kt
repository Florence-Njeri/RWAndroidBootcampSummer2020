package com.florencenjeri.readinglist

import android.app.Application
import android.content.Context
import com.florencenjeri.readinglist.prefs.SharedPrefsHelper
import com.florencenjeri.readinglist.prefs.UserPrefs

class ReadingListApplication : Application() {

    companion object {
        private lateinit var instance: ReadingListApplication

        fun getAppContext(): Context = instance.applicationContext
        val prefsHelper: SharedPrefsHelper by lazy { UserPrefs() }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

    }
}