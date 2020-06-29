package com.florencenjeri.readinglist

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.florencenjeri.readinglist.model.Author
import com.florencenjeri.readinglist.model.Books
import com.florencenjeri.readinglist.model.BooksDao
import com.florencenjeri.readinglist.model.BooksDatabase
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
            database = Room.inMemoryDatabaseBuilder(context, BooksDatabase::class.java)
                .allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.i("test", e.message!!)
        }
        booksDao = database.booksDao()
    }

    @Test
    fun testInsertingAndRetrievingFromDb() {
        val initialRetrievedBooks = booksDao.getAll()

        val books = Books(
            1,
            R.drawable.we_need_new_names,
            "We Need New Names",
            Author("NoViolet Bulawayo", "Zimbabwean"),
            "May 21st 2013",
            "298",
            "An exciting literary debut: the unflinching and powerful story of a young girl's journey out of Zimbabwe and to America.\n" +
                    "\n" +
                    "Darling is only ten years old, and yet she must navigate a fragile and violent world. In Zimbabwe, Darling and her friends steal guavas, try to get the baby out of young Chipo's belly, and grasp at memories of Before. Before their homes were destroyed by paramilitary policemen, before the school closed, before the fathers left for dangerous jobs abroad.\n" +
                    "\n" +
                    "But Darling has a chance to escape: she has an aunt in America. She travels to this new land in search of America's famous abundance only to find that her options as an immigrant are perilously few. NoViolet Bulawayo's debut calls to mind the great storytellers of displacement and arrival who have come before her--from Junot Diaz to Zadie Smith to J.M. Coetzee--while she tells a vivid, raw story all her own.",
            "Fiction"
        )
        booksDao.putAll(books)

        val retrievedBooks = booksDao.getAll()

        val sizeDiff = retrievedBooks.size - initialRetrievedBooks.size

        Assert.assertEquals(1, sizeDiff)
        val getbooks = retrievedBooks.last()
        Assert.assertEquals("298", getbooks.pages)
    }

    @After
    fun cleanUp() {
        database.close()
    }
}