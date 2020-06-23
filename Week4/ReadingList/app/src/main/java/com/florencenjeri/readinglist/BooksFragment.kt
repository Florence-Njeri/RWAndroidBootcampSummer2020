package com.florencenjeri.readinglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.florencenjeri.readinglist.model.Books
import com.florencenjeri.readinglist.model.BooksData

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BooksFragment : Fragment(), BooksAdapter.BooksListClickListener {
    val booksList = BooksData.booksRead
    lateinit var booksRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.books_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        booksRecyclerView = view.findViewById(R.id.books_list)
        booksRecyclerView.adapter = BooksAdapter(booksList.toTypedArray(), this)
        postponeEnterTransition()
        startPostponedEnterTransition()
    }

    fun sortList(genre: String) {
        val filteredList = booksList.filter { it.genre == genre }
        booksRecyclerView.adapter = BooksAdapter(filteredList.toTypedArray(), this)


    }

    override fun listItemClicked(books: Books) {
        val action = BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(books)
        findNavController().navigate(action)
    }
}