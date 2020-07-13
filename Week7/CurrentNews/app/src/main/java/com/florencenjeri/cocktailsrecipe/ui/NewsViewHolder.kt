package com.florencenjeri.cocktailsrecipe.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florencenjeri.cocktailsrecipe.model.New
import kotlinx.android.synthetic.main.news_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun setBookData(new: New) {

        itemView.title.text = new.title
        itemView.author.text = new.author
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault())
        val date = dateFormat.parse(new.published)
        val formatter = SimpleDateFormat("yyyy-MM-dd") //If you need time just put specific format for time like ‘HH:mm:ss’
        val dateStr = formatter.format(date)
        itemView.publicationDate.text = dateStr
        itemView.language.text = new.language
        Glide.with(itemView)
            .load(new.image)
            .centerCrop()
            .into(itemView.image)
    }
}