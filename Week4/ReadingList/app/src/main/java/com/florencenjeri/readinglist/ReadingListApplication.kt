package com.florencenjeri.readinglist

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.florencenjeri.readinglist.model.BooksDatabase

class ReadingListApplication : Application() {

    companion object {
        private lateinit var instance: ReadingListApplication

        //Using a singleton pattern sing Room is resource intensive
        var database: BooksDatabase? = null

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        database =
            Room.databaseBuilder(this, BooksDatabase::class.java, "reading-list-db").build()
        super.onCreate()
    }
}