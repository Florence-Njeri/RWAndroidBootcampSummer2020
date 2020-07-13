package com.florencenjeri.cocktailsrecipe.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.florencenjeri.cocktailsrecipe.model.New

@Database(entities = [(New::class)], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(
            context: Context
        ): NewsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    NEWS_DB

                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        const val NEWS_DB = "news-db"
    }

}
