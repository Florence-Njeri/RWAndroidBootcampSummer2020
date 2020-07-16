package com.florencenjeri.readinglist.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florencenjeri.readinglist.model.Books
import kotlinx.android.synthetic.main.book_list_item.view.*

class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun setBookData(books: Books) {

        itemView.bookTitle.text = books.title
        itemView.author.text = books.author.name
        itemView.publicationDate.text = books.publicationDate
        itemView.numPages.text = books.pages
        itemView.genre.text = books.genre
        Glide.with(itemView)
            .load(books.image)
            .centerCrop()
            .into(itemView.cover)
    }
}