package com.florencenjeri.cocktailsrecipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.florencenjeri.cocktailsrecipe.model.Success
import com.florencenjeri.cocktailsrecipe.ui.NewsAdapter
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//       newsList.adapter = NewsAdapter( App.newsRepository.fetchNews(), this)

        GlobalScope.launch(Dispatchers.Main) {
            val result = App.newsRepository.fetchNews()
            if (result is Success) {
                App.newsDao.fetchNews().observe(viewLifecycleOwner, Observer {
                    newsList.adapter = NewsAdapter(it)
                    Log.d("News", it.toString())
                })
            }

        }
    }
}