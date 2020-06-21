package com.florencenjeri.readinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florencenjeri.readinglist.model.Books

class BooksAdapter(private val books: Array<Books>) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var bookTitle: TextView = view.findViewById(R.id.book_title)
        private var author: TextView = view.findViewById(R.id.author)
        private var publicationDate: TextView = view.findViewById(R.id.publication_date)
        private var pages: TextView = view.findViewById(R.id.num_pages)
        private var genre: TextView = view.findViewById(R.id.genre)
        private var bookCover: ImageView = view.findViewById(R.id.cover)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            v.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        fun bindPhoto(books: Books) {
            //TODO: Use Glide for book cover  image loading
            Glide.with(itemView)
                .load(books.image)
                .centerCrop()
                .into(bookCover) //8
            bookTitle.text = books.title
            author.text = books.author.name
            publicationDate.text = books.publicationDate
            pages.text = books.pages
            genre.text = books.genre

        }
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
        holder.bindPhoto(itemBook)

    }
}