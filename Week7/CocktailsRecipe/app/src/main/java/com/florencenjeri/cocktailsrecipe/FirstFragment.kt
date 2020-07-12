package com.florencenjeri.cocktailsrecipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
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

                newsList.adapter = NewsAdapter( result.data)

            }

            Log.d("News", App.newsRepository.fetchNews().toString())

        }
    }
}