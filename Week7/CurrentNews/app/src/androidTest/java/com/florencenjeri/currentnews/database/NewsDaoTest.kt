package com.florencenjeri.currentnews.database

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.florencenjeri.currentnews.App
import com.florencenjeri.currentnews.model.News
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDaoTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()
    private lateinit var database: NewsDatabase
    private lateinit var booksDao: NewsDao

    @Before
    fun setup() {
        val context: Context = App.getAppContext()
        try {
            //Use in memory db so its created in the system memory once your app is destroyed it does not persist
            database = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
                .allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.i("test", e.message!!)
        }
        booksDao = database.newsDao()
    }

    /** runBlocking will block the current thread until the coroutine finishes executing */
    @Test
    fun testInsertingAndRetrievingFromDb() = runBlocking {
        val newsList = ArrayList<News>()

        newsList.add(
            News(
                "PR Newswire",
                "RIYADH, Saudi Arabia, July 25, 2020 /PRNewswire/ -- At the invitation of the United Nations Development Programme (UNDP), the Saudi Development and Reconstruction Program for Yemen (SDRPY) participated in a virtual international donors meeting on Thursday on development priorities in the aftermath o...",
                "2c776fa0-9c43-46c0-8f78-88eb54aa733a",
                "https://mma.prnewswire.com/media/1219520/UNDP___SDRPY_Logo.jpg",
                "en",
                "2020-07-25 18:04:00 +0000",
                "SDRPY, UNDP and Key Developmental Actors in Yemen Highlight Humanitarian-Development-Peace Nexus – Company Announcement",
                "https://markets.ft.com/data/announce/detail?dockey=600-202007251404PR_NEWS_USPRX____PH73724-1"
            )
        )

        booksDao.insertNews(newsList)

        val retrievedBooks = booksDao.fetchNews()

        retrievedBooks.observeForever {
            Assert.assertEquals(1, it.size)
            val lastBookItem = it.last()
            Assert.assertEquals("en", lastBookItem.language)
        }
    }

    @After
    fun cleanUp() {
        database.close()
    }
}