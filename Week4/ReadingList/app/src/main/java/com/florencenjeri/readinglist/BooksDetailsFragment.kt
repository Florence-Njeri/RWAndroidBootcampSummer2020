package com.florencenjeri.readinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.book_details_content.view.*
import kotlinx.android.synthetic.main.book_list_item.*
import kotlinx.android.synthetic.main.books_details_fragment.*

class BooksDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.books_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCardTransitionOnEnter()
        arguments?.let {
            val safeArgs = BooksDetailsFragmentArgs.fromBundle(it)
            itemImageView.setImageResource(safeArgs.book.image)
            bookCover.setImageResource(safeArgs.book.image)
            bookDetailsContent.bookTitle.text = safeArgs.book.title
            bookDetailsContent.publicationDate.text = safeArgs.book.publicationDate
            bookDetailsContent.pages.text = safeArgs.book.pages
            bookDetailsContent.genre.text = safeArgs.book.genre

            //Author
            bookDetailsContent.author.text = safeArgs.book.author.name
            bookDetailsContent.nationality.text = safeArgs.book.author.nationality

            //Synopsis
            bookDetailsContent.synopsis.text = safeArgs.book.synopsis

        }
        activity?.toolbar?.title = bookTitle.text
    }

    private fun setCardTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.card_transition)
    }
}