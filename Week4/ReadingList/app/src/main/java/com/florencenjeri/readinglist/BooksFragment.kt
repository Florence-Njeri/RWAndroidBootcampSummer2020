package com.florencenjeri.readinglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.florencenjeri.readinglist.model.BooksData

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BooksFragment : Fragment() {
    val booksList = BooksData.booksRead
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.books_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bookRecyclerView = view.findViewById<RecyclerView>(R.id.books_list)
        bookRecyclerView.adapter = BooksAdapter(booksList.toTypedArray())

    }
}