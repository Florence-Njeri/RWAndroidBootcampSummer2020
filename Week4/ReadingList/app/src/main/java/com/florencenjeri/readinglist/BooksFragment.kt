package com.florencenjeri.readinglist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.florencenjeri.readinglist.model.Books
import com.florencenjeri.readinglist.model.UserPrefs
import com.florencenjeri.readinglist.model.database.BooksDatabase
import com.florencenjeri.readinglist.model.database.BooksRepository
import kotlinx.android.synthetic.main.books_fragment.view.*

class BooksFragment : Fragment(), BooksAdapter.BooksListClickListener {

    private lateinit var booksViewModel: BooksViewModel
    private val userPrefs = UserPrefs()
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
        val booksDao =
            BooksDatabase.getDatabase(ReadingListApplication.getAppContext(), lifecycleScope)
                .booksDao()
        val booksRepository = BooksRepository(booksDao)
        booksViewModel =
            ViewModelProviders.of(this, BooksViewModelFactory(booksRepository))
                .get(BooksViewModel::class.java)

        booksViewModel.getReadBooks().observe(viewLifecycleOwner, Observer {
            view.booksList.adapter = BooksAdapter(it, this)

        })

    }

    private fun sortList(genre: String) {
//        val filteredList = booksList.filter { it.genre == genre }
//        view?.booksList?.adapter = BooksAdapter(filteredList.toList(), this)
    }

    override fun listItemClicked(books: Books) {
        val action = BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(books.bookId)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Sort the list
        if (item.groupId == R.id.menu_sort_group) {
            sortBooksById(item.itemId)
            item.isChecked = true
        } else if (item.itemId == R.id.action_logout) {
            //Log out and navigate to LogInFragment
            userPrefs.logOut()
            val action = BooksFragmentDirections.actionBooksFragmentToLogInFragment()
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortBooksById(itemId: Int) {
        when (itemId) {
            R.id.action_fiction -> sortList("Fiction")
            else -> sortList("Self Help")
        }
    }

}