package com.florencenjeri.currentnews.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.florencenjeri.currentnews.App
import com.florencenjeri.currentnews.R
import com.florencenjeri.currentnews.model.News
import com.florencenjeri.currentnews.ui.adapter.EndlessRecyclerViewScrollListener
import com.florencenjeri.currentnews.ui.adapter.NewsAdapter
import com.florencenjeri.currentnews.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.android.viewmodel.ext.android.viewModel


class NewsFragment : Fragment() {

    val newsAdapter by lazy {
        NewsAdapter(
            ::readMoreOnBrowser
        )
    }
    //Fetch the ViewModel Instance using Koin
    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.refreshNewsInDb()
        newsViewModel.fetchNews().observe(viewLifecycleOwner, Observer() {
            newsAdapter.submitList(it)
            newsList.adapter = newsAdapter
        })
        val linearLayoutManager = LinearLayoutManager(App.getAppContext())

        // Retain an instance so that you can call `resetState()` for fresh searches
        val scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list

                loadNextDataFromDatabase(page)
            }
        }
        // Adds the scroll listener to RecyclerView
        newsList.addOnScrollListener(scrollListener)
    }

    private fun loadNextDataFromDatabase(page: Int) {

    }

    private fun readMoreOnBrowser(news: News?) {
        val uri = Uri.parse(news?.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
