package com.florencenjeri.readinglist.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.florencenjeri.readinglist.R
import com.florencenjeri.readinglist.ReadingListApplication
import com.florencenjeri.readinglist.model.Books
import kotlinx.android.synthetic.main.book_list_item.*
import kotlinx.android.synthetic.main.books_fragment.view.*
import kotlinx.coroutines.launch

class BooksFragment : Fragment(), BooksAdapter.BooksListClickListener {

    private lateinit var booksViewModel: BooksViewModel

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

        booksViewModel =
            ViewModelProviders.of(this).get(BooksViewModel::class.java)
        lifecycleScope.launch {
            populateRecyclerView(booksViewModel.getReadBooks())
        }
    }

    private fun populateRecyclerView(filteredList: List<Books>) {
        view?.booksList?.adapter = BooksAdapter(filteredList, this)
        (view?.booksList?.adapter as BooksAdapter).notifyDataSetChanged()
    }

    override fun listItemClicked(books: Books) {
        //Navigate using shared element animations on item click

        //Make the fragment transitions
        val imagePair = kotlin.Pair(cover as View, "tImage")
        val extraInfoForSharedElement = FragmentNavigatorExtras(
            imagePair
        )
        val action =
            BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(
                books.bookId
            )
        navigate(action, extraInfoForSharedElement)
    }

    private fun navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) =
        with(findNavController()) {

            currentDestination?.getAction(destination.actionId)
                ?.let {
                    navigate(destination, extraInfo)
                }
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
            ReadingListApplication.prefsHelper.logOut()
            val action =
                BooksFragmentDirections.actionBooksFragmentToLogInFragment()
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortBooksById(itemId: Int) {
        when (itemId) {
            R.id.action_fiction -> {
                lifecycleScope.launch {
                    val newList = booksViewModel.sortData("Fiction")
                    populateRecyclerView(newList)
                }
            }
            else -> {
                lifecycleScope.launch {
                    val newList = booksViewModel.sortData("Self Help")
                    populateRecyclerView(newList)
                }
            }
        }
    }

}