package com.florencenjeri.cocktailsrecipe.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florencenjeri.cocktailsrecipe.App
import com.florencenjeri.cocktailsrecipe.R
import com.florencenjeri.cocktailsrecipe.model.News
import kotlinx.android.synthetic.main.news_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun setBookData(new: News) {
        val context = App.getAppContext()
        itemView.title.text = new.title
        itemView.author.text = context.getString(R.string.author, new.author)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault())
        val date = dateFormat.parse(new.published)
        val formatter =
            SimpleDateFormat("yyyy-MM-dd") //If you need time just put specific format for time like ‘HH:mm:ss’
        val dateStr = formatter.format(date)
        itemView.publicationDate.text = context.getString(R.string.publication_date, dateStr)
        itemView.language.text = context.getString(R.string.language, new.language)
        Glide.with(itemView)
            .load(new.image)
            .centerCrop()
            .placeholder(R.drawable.ic_image_place_holder) //5
            .error(R.drawable.ic_broken_image) //6
            .fallback(R.drawable.ic_no_image) //7
            .into(itemView.image)
    }
}