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
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.florencenjeri.cocktailsrecipe.R
import com.florencenjeri.cocktailsrecipe.database.NewsRepository
import com.florencenjeri.cocktailsrecipe.model.News
import com.florencenjeri.cocktailsrecipe.worker.PeriodicNewsSyncWorker
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.concurrent.TimeUnit

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
        viewModel.fetchNews().observe(viewLifecycleOwner, Observer {
            newsAdapter.setData(it)
            newsList.adapter = newsAdapter
            Log.d("News", it.toString())
        })

        val constraints = Constraints.Builder()
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val work = PeriodicWorkRequestBuilder<PeriodicNewsSyncWorker>(50, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()
        val workManager = WorkManager.getInstance(requireContext())
        workManager.enqueue(work)

    }

    private fun onListButtonClicked(news: News) {
        val uri = Uri.parse(news.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}