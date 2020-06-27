package com.florencenjeri.readinglist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florencenjeri.readinglist.model.Books
import kotlinx.android.synthetic.main.book_list_item.view.*

class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var bookTitle = view.bookTitle
    private var author: TextView = view.author
    private var publicationDate: TextView = view.publicationDate
    private var pages: TextView = view.numPages
    private var genre: TextView = view.genre
    private var bookCover: ImageView = view.cover

    fun bindPhoto(books: Books) {
        Glide.with(itemView)
            .load(books.image)
            .centerCrop()
            .into(bookCover)
        bookTitle.text = books.title
        author.text = books.author.name
        publicationDate.text = books.publicationDate
        pages.text = books.pages
        genre.text = books.genre

    }
}