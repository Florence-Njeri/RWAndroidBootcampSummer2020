package com.florencenjeri.currentnews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.florencenjeri.currentnews.R
import com.florencenjeri.currentnews.model.News
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(private val onListButtonClicked: (News) -> Unit) :
    RecyclerView.Adapter<NewsViewHolder>() {
    private val data: MutableList<News> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //Show the right view depending on the users position in scrolling
        val itemBook = data[position]
        holder.setBookData(itemBook)
        holder.itemView.readMore.setOnClickListener {
            onListButtonClicked(itemBook)
        }
    }

    fun setData(data: List<News>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

}