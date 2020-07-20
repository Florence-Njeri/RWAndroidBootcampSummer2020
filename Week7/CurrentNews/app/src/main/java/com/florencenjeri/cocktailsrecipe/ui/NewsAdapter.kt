package com.florencenjeri.cocktailsrecipe.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.florencenjeri.cocktailsrecipe.R
import com.florencenjeri.cocktailsrecipe.model.News
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(private val onListButtonClicked: (News?) -> Unit) :
    PagingDataAdapter<News, NewsViewHolder>(diffUtil) {
    private val data: MutableList<News> = mutableListOf()

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //Show the right view depending on the users position in scrolling
        val itemBook =getItem(position)
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