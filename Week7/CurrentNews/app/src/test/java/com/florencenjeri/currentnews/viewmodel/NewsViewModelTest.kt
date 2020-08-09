package com.florencenjeri.currentnews.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.florencenjeri.currentnews.database.NewsRepository
import com.florencenjeri.currentnews.di.networkModule
import com.florencenjeri.currentnews.di.newsModule
import com.florencenjeri.currentnews.model.News
import com.florencenjeri.currentnews.ui.viewmodel.NewsViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NewsViewModelTest : KoinTest{

    private lateinit var viewModel: NewsViewModel
    //We need to mock the repository dependency
    val appContext = ApplicationProvider.getApplicationContext<Context>()
    private val repository: NewsRepository by inject()
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

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }
    @Before
    fun setUp() {
        startKoin {
            //For logging Koin related errors
            androidLogger()
            //Declare my app context
            androidContext(appContext)
            modules(listOf(newsModule, networkModule))
        }
        declareMock<NewsRepository>()
        Mockito.`when`(repository.fetchNews()).thenReturn(newsLiveData)
        viewModel = NewsViewModel()
    }

    @Test
    fun `check if there is news data in repository`() {
        //Given that the news data is :
        newsLiveData.value = newsList

        //When
        viewModel.fetchNews().observeForever { news ->
            //Then
            assertThat(news.size, CoreMatchers.`is`(2))
        }
        //Verify that data is fetched from the local database
        Mockito.verify(repository).fetchNews()
    }

}