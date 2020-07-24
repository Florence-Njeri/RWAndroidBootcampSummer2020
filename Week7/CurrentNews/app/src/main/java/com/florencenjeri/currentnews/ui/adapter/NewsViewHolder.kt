package com.florencenjeri.currentnews.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florencenjeri.currentnews.App
import com.florencenjeri.currentnews.R
import com.florencenjeri.currentnews.model.News
import kotlinx.android.synthetic.main.news_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun setBookData(news: News?) {
        val context = App.getAppContext()
        itemView.title.text = news?.title
        itemView.author.text = context.getString(R.string.author, news?.author)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault())
        val date = dateFormat.parse(news?.published)
        val formatter =
            SimpleDateFormat("yyyy-MM-dd") //If you need time just put specific format for time like ‘HH:mm:ss’
        val dateStr = formatter.format(date)
        itemView.publicationDate.text = context.getString(R.string.publication_date, dateStr)
        itemView.language.text = context.getString(R.string.language, news?.language)
        Glide.with(itemView)
            .load(news?.image)
            .centerCrop()
            .placeholder(R.drawable.ic_image_place_holder) //5
            .error(R.drawable.ic_broken_image) //6
            .fallback(R.drawable.ic_no_image) //7
            .into(itemView.image)
    }
}