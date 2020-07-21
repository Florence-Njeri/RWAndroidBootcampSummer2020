package com.florencenjeri.cocktailsrecipe.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.florencenjeri.cocktailsrecipe.App
import com.florencenjeri.cocktailsrecipe.R
import com.florencenjeri.cocktailsrecipe.database.NewsRepository
import com.florencenjeri.cocktailsrecipe.model.News
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {
    val newsRepository by lazy { NewsRepository() }
    val newsAdapter by lazy { NewsAdapter(::onListButtonClicked) }
    private val viewModel by lazy {
        ViewModelProvider(this, NewsViewModelFactory(newsRepository)).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.insertNewsToDb()
        viewModel.fetchNews().observe(viewLifecycleOwner, Observer() {
            newsAdapter.setData(it)
            newsList.adapter = newsAdapter
            Log.d("NewsList", it.toString())
        })
        val linearLayoutManager = LinearLayoutManager(App.getAppContext())

        // Retain an instance so that you can call `resetState()` for fresh searches
        // Retain an instance so that you can call `resetState()` for fresh searches
        val scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromDatabase(page)
            }
        }
        // Adds the scroll listener to RecyclerView
        // Adds the scroll listener to RecyclerView
        newsList.addOnScrollListener(scrollListener)

    }

    private fun loadNextDataFromDatabase(page: Int) {

    }

    private fun onListButtonClicked(news: News?) {
        val uri = Uri.parse(news?.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}