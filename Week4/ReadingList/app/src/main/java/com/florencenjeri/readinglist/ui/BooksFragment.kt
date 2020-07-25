package com.florencenjeri.readinglist.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.florencenjeri.readinglist.R
import com.florencenjeri.readinglist.ReadingListApplication
import com.florencenjeri.readinglist.model.Books
import kotlinx.android.synthetic.main.books_fragment.view.*

class BooksFragment : Fragment(), BooksAdapter.BooksListClickListener {

    val booksViewModelFactory by lazy { BooksViewModelFactory(ReadingListApplication.repository) }
    val booksViewModel by lazy {
        ViewModelProviders.of(this, booksViewModelFactory).get(BooksViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.books_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        booksViewModel.getReadBooks().observe(viewLifecycleOwner, Observer {
            populateRecyclerView(it)
        })
    }

    private fun populateRecyclerView(bookList: List<Books>) {
        view?.booksList?.adapter = BooksAdapter(bookList, this)
        (view?.booksList?.adapter as BooksAdapter).notifyDataSetChanged()
    }

    override fun listItemClicked(books: Books) {
        val action =
            BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(
                books.bookId
            )
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Sort the list
        if (item.groupId == R.id.menu_sort_group) {
            booksViewModel.sortData(
                when (item.itemId) {
                    R.id.action_fiction -> FilterType.FICTION
                    R.id.action_self_help -> FilterType.SELF_HELP
                    else -> FilterType.ALL_BOOKS
                }
            )
            item.isChecked = true
        } else if (item.itemId == R.id.action_logout) {
            //Log out and navigate to LogInFragment
            ReadingListApplication.prefsHelper.logOut()
            val action =
                BooksFragmentDirections.actionBooksFragmentToLogInFragment()
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }
}