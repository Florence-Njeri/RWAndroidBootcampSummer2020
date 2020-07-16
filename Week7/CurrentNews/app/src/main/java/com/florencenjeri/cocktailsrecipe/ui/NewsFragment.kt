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
import com.florencenjeri.cocktailsrecipe.R
import com.florencenjeri.cocktailsrecipe.model.New
import com.florencenjeri.cocktailsrecipe.database.NewsRepository
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(), NewsAdapter.NewsListClickListener {
    val newsRepository by lazy { NewsRepository() }
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            NewsViewModelFactory(
                newsRepository
            )
        )
            .get(NewsViewModel::class.java)
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

        viewModel.fetchNews().observe(viewLifecycleOwner, Observer {
            newsList.adapter = NewsAdapter(it, this@NewsFragment)
            Log.d("News", it.toString())
        })

    }

    override fun listButtonClicked(news: New) {
        val uri = Uri.parse(news.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}