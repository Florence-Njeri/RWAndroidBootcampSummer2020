package com.florencenjeri.readinglist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.florencenjeri.readinglist.R
import com.florencenjeri.readinglist.model.Books

class BooksAdapter(
    private val books: List<Books>,
    private val clickListener: BooksListClickListener
) :
    RecyclerView.Adapter<BooksViewHolder>() {
    interface BooksListClickListener {
        fun listItemClicked(books: Books)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_item, parent, false)
        return BooksViewHolder(view)

    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        //Show the right view depending on the users position in scrolling
        val itemBook = books[position]
        holder.setBookData(itemBook)
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(itemBook)
        }

    }


}