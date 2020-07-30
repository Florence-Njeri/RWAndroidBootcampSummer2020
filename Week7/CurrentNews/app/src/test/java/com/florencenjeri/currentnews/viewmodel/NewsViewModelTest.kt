package com.florencenjeri.currentnews.viewmodel

import androidx.lifecycle.MutableLiveData
import com.florencenjeri.currentnews.database.NewsRepository
import com.florencenjeri.currentnews.model.News
import com.florencenjeri.currentnews.ui.viewmodel.NewsViewModel
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NewsViewModelTest {

    private lateinit var viewModel: NewsViewModel
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

    //We need to mock the repository dependency
    @Mock
    private lateinit var repository: NewsRepository

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        Mockito.`when`(repository.fetchNews()).thenReturn(newsLiveData)
        viewModel = NewsViewModel(repository)
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
        verify(repository).fetchNews()
    }

}