package com.florencenjeri.cocktailsrecipe.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florencenjeri.cocktailsrecipe.model.New
import kotlinx.android.synthetic.main.news_item.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun setBookData(new: New) {

        itemView.title.text = new.title
        itemView.author.text = new.author
        itemView.publicationDate.text = new.published
        itemView.language.text = new.language
        Glide.with(itemView)
            .load(new.image)
            .centerCrop()
            .into(itemView.image)
    }
}