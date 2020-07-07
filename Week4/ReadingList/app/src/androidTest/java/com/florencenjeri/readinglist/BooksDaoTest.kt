package com.florencenjeri.readinglist

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.florencenjeri.readinglist.database.BooksDao
import com.florencenjeri.readinglist.database.BooksDatabase
import com.florencenjeri.readinglist.model.BooksData
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BooksDaoTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()
    private lateinit var database: BooksDatabase
    private lateinit var booksDao: BooksDao

    @Before
    fun setup() {
        val context: Context = ReadingListApplication.getAppContext()
        try {
            //Use in memory db so its created in the system emory once your app is destroyed it does not persist
            database = Room.inMemoryDatabaseBuilder(context, BooksDatabase::class.java)
                .allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.i("test", e.message!!)
        }
        booksDao = database.booksDao()
    }
/** runBlocking will block the current thread until the coroutine finishes executing */
    @Test
    fun testInsertingAndRetrievingFromDb() = runBlocking {

        booksDao.putAll(BooksData.booksRead.toList())

        val retrievedBooks = booksDao.getAll()

        retrievedBooks.observeForever {
            Assert.assertEquals(10, it.size)
            val lastBookItem = it.last()
            Assert.assertEquals("304", lastBookItem.pages)
        }

    }

    @After
    fun cleanUp() {
        database.close()
    }
}