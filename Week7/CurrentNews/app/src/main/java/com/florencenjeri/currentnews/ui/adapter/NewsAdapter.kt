package com.florencenjeri.currentnews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.florencenjeri.currentnews.databinding.NewsItemBinding
import com.florencenjeri.currentnews.model.News
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(private val onListButtonClicked: (News) -> Unit) :
    ListAdapter<News, NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(layoutInflater,parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //Show the right view depending on the users position in scrolling
        val itemBook = getItem(position)
        holder.setBookData(itemBook)
        holder.itemView.readMore.setOnClickListener {
            onListButtonClicked(itemBook)
        }
    }

}

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    //Checks if it is the same news item
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    //Check whether the oldItem and the newItem contain the same ata
    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}