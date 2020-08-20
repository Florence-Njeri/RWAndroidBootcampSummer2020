package com.florencenjeri.readinglist.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.florencenjeri.readinglist.R
import com.florencenjeri.readinglist.model.Books
import com.florencenjeri.readinglist.utils.Constants.TRANSFORMATION_DURATION
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.book_details_content.view.*
import kotlinx.android.synthetic.main.books_details_fragment.*

class BooksDetailsFragment : Fragment() {
    private lateinit var booksViewModel: BooksViewModel
    private lateinit var book: Books
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.books_details_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = buildContainerTransform()
        sharedElementReturnTransition = buildContainerTransform()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        booksViewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)
        arguments?.let {
            val safeArgs =
                BooksDetailsFragmentArgs.fromBundle(
                    it
                )
            booksViewModel.getBook(safeArgs.bookId).observe(viewLifecycleOwner, Observer { book ->
                this.book = book
                displayBookDetails(book)
                activity?.toolbar?.title = book.title
            })
        }
    }

    private fun displayBookDetails(book: Books) {
        bookCoverImageView.setImageResource(book.image)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_book, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_book) {
            deleteBook()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteBook() {
        Log.d("Books", book.toString())
        booksViewModel.deleteBook(book)
    }

    //Animation Container Transform
    private fun buildContainerTransform() =
        MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = TRANSFORMATION_DURATION
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_THROUGH
        }
}