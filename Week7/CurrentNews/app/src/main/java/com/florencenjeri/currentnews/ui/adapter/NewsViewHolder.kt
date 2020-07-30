package com.florencenjeri.currentnews.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florencenjeri.currentnews.App
import com.florencenjeri.currentnews.R
import com.florencenjeri.currentnews.databinding.NewsItemBinding
import com.florencenjeri.currentnews.model.News
import java.text.SimpleDateFormat
import java.util.*

class NewsViewHolder(val binding:  NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setBookData(news: News?) {
        val context = App.getAppContext()
        binding.title.text = news?.title
       binding.author.text = context.getString(R.string.author, news?.author)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault())
        val date = dateFormat.parse(news?.published)
        val formatter =
            SimpleDateFormat("yyyy-MM-dd") //If you need time just put specific format for time like ‘HH:mm:ss’
        val dateStr = formatter.format(date)
        binding.publicationDate.text = context.getString(R.string.publication_date, dateStr)
        binding.language.text = context.getString(R.string.language, news?.language)
        Glide.with(itemView)
            .load(news?.image)
            .centerCrop()
            .placeholder(R.drawable.ic_image_place_holder)
            .error(R.drawable.ic_broken_image)
            .fallback(R.drawable.ic_no_image)
            .into(binding.image)
    }
}