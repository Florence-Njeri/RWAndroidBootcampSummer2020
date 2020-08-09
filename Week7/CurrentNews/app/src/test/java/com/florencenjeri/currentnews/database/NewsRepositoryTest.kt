package com.florencenjeri.currentnews.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.florencenjeri.currentnews.di.networkModule
import com.florencenjeri.currentnews.di.newsModule
import com.florencenjeri.currentnews.model.News
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NewsRepositoryTest : KoinTest {
    val appContext = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var newsRepository: NewsRepository
    private val dao: NewsDao by inject()

    private val newsList = listOf(
        News(
            "PR Newswire",
            "RIYADH, Saudi Arabia, July 25, 2020 /PRNewswire/ -- At the invitation of the United Nations Development Programme (UNDP), the Saudi Development and Reconstruction Program for Yemen (SDRPY) participated in a virtual international donors meeting on Thursday on development priorities in the aftermath o...",
            "2c776fa0-9c43-46c0-8f78-88eb54aa733a",
            "https://mma.prnewswire.com/media/1219520/UNDP___SDRPY_Logo.jpg",
            "en",
            "2020-07-25 18:04:00 +0000",
            "SDRPY, UNDP and Key Developmental Actors in Yemen Highlight Humanitarian-Development-Peace Nexus – Company Announcement",
            "https://markets.ft.com/data/announce/detail?dockey=600-202007251404PR_NEWS_USPRX____PH73724-1"
        ),
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

    @Spy
    val newsLiveData: MutableLiveData<List<News>> = MutableLiveData()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        startKoin {
            //For logging Koin related errors
            androidLogger()
            //Declare my app context
            androidContext(appContext)
            modules(listOf(newsModule, networkModule))
        }
        dao.fetchNews()
        newsRepository = NewsRepository()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `check that data is being fetched from room db`() {
        //Given that data is inserted into the database
        newsLiveData.value = newsList
        //When you fetch data from the database
        val news: LiveData<List<News>> = newsRepository.fetchNews()

        //Then the list of news should not be empty
        news.observeForever { news ->
            assertNotNull(news.size)
        }

    }

}