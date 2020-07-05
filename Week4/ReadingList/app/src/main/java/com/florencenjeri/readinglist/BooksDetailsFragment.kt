package com.florencenjeri.readinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.florencenjeri.readinglist.model.Books
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.book_details_content.view.*
import kotlinx.android.synthetic.main.book_list_item.*
import kotlinx.android.synthetic.main.books_details_fragment.*

class BooksDetailsFragment : Fragment() {
    private lateinit var booksViewModel: BooksViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.books_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        booksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)


        arguments?.let {
            val safeArgs = BooksDetailsFragmentArgs.fromBundle(it)
            booksViewModel.getBook(safeArgs.bookId).observe(viewLifecycleOwner, Observer { book ->

                displayBookDetails(book)
            })

        }
        activity?.toolbar?.title = bookTitle.text
    }

    private fun displayBookDetails(book: Books) {
        itemImageView.setImageResource(book.image)
        bookCover.setImageResource(book.image)
        bookDetailsContent.bookTitle.text = book.title
        bookDetailsContent.publicationDate.text = book.publicationDate
        bookDetailsContent.pages.text = book.pages
        bookDetailsContent.genre.text = book.genre

        //Author
        bookDetailsContent.author.text = book.author.name
        bookDetailsContent.nationality.text = book.author.nationality

        //Synopsis
        bookDetailsContent.synopsis.text = book.synopsis
    }

    private fun displayBookDetails() {

    }

    private fun setCardTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.card_transition)
    }
}