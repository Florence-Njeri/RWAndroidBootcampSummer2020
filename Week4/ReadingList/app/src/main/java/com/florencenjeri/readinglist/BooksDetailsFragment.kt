package com.florencenjeri.readinglist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.book_details_content.view.*
import kotlinx.android.synthetic.main.book_list_item.view.book_title
import kotlinx.android.synthetic.main.book_list_item.view.publication_date
import kotlinx.android.synthetic.main.books_details_fragment.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
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
        arguments?.let {
            val safeArgs = BooksDetailsFragmentArgs.fromBundle(it)
            itemImageView.setImageResource(safeArgs.book.image)
            bookCover.setImageResource(safeArgs.book.image)
            book_details_content.book_title.text = safeArgs.book.title
            book_details_content.publication_date.text = safeArgs.book.publicationDate
            book_details_content.pages.text = safeArgs.book.pages
            book_details_content.genre.text = safeArgs.book.genre

            //Author
            book_details_content.name.text = safeArgs.book.author.name
            book_details_content.nationality.text = safeArgs.book.author.nationality

            //Synopsis
            book_details_content.synopsis_textview.text = safeArgs.book.synopsis
            activity?.let{
                it.toolbar.title = safeArgs.book.title
            }
        }

    }
}