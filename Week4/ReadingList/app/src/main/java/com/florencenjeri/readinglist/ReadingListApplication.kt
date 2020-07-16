package com.florencenjeri.readinglist

import android.app.Application
import android.content.Context
import com.florencenjeri.readinglist.database.BooksDatabase
import com.florencenjeri.readinglist.database.BooksRepository
import com.florencenjeri.readinglist.prefs.SharedPrefsHelper
import com.florencenjeri.readinglist.prefs.UserPrefs
import kotlinx.coroutines.GlobalScope

class ReadingListApplication : Application() {

    companion object {
        private lateinit var instance: ReadingListApplication

        fun getAppContext(): Context = instance.applicationContext
        val prefsHelper: SharedPrefsHelper by lazy { UserPrefs() }
        private lateinit var booksDatabase: BooksDatabase
        val dao by lazy { booksDatabase.booksDao() }
        val repository by lazy { BooksRepository(dao) }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        booksDatabase = BooksDatabase.getDatabase(this, GlobalScope)

    }
}