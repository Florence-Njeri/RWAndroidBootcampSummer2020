package com.florencenjeri.cocktailsrecipe.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.florencenjeri.cocktailsrecipe.R
import com.florencenjeri.cocktailsrecipe.model.New

class NewsAdapter(
    private val books: List<New>
//    private val clickListener: NewsListClickListener
) :
    RecyclerView.Adapter<NewsViewHolder>() {
    interface NewsListClickListener {
        fun listItemClicked(books: New)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)

    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //Show the right view depending on the users position in scrolling
        val itemBook = books[position]
        holder.setBookData(itemBook)
//        holder.itemView.setOnClickListener {
//            clickListener.listItemClicked(itemBook)
//        }

    }


}