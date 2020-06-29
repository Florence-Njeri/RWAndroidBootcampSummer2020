package com.florencenjeri.readinglist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.florencenjeri.readinglist.model.Books
import com.florencenjeri.readinglist.model.BooksDao
import com.florencenjeri.readinglist.model.BooksData
import com.florencenjeri.readinglist.model.BooksDatabase
import kotlinx.android.synthetic.main.books_fragment.view.*

class BooksFragment : Fragment(), BooksAdapter.BooksListClickListener {
    //    val booksList = BooksData.booksRead
    private lateinit var database: BooksDatabase
    private lateinit var booksDao: BooksDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.books_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set up our database
        database = ReadingListApplication.database!!
        booksDao = database.booksDao()
        if (booksDao.getAll().isEmpty()) {
            booksDao.putAll(BooksData.booksRead.toTypedArray())
        }

        view.booksList.adapter = BooksAdapter(booksDao.getAll().toTypedArray(), this)
        postponeEnterTransition()
        startPostponedEnterTransition()
    }

    private fun sortList(genre: String) {
        val filteredList = booksDao.getAll().filter { it.genre == genre }
        view?.booksList?.adapter = BooksAdapter(filteredList.toTypedArray(), this)
    }

    override fun listItemClicked(books: Books) {
        val action = BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(books)
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