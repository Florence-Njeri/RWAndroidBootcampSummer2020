package com.florencenjeri.readinglist.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.florencenjeri.readinglist.R
import com.florencenjeri.readinglist.ReadingListApplication
import com.florencenjeri.readinglist.model.Books
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.book_list_item.*
import kotlinx.android.synthetic.main.books_fragment.view.*
import kotlinx.coroutines.launch

class BooksFragment : Fragment(), BooksAdapter.BooksListClickListener {

    private lateinit var booksViewModel: BooksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zTransitionFromLogInFragment()
    }

    private fun zTransitionFromLogInFragment() {
        //From Login to Books Fragment
        val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        enterTransition = forward
        //Back to the LogIn Fragment
        val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        returnTransition = backward
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
        booksViewModel =
            ViewModelProviders.of(this).get(BooksViewModel::class.java)
        lifecycleScope.launch {
            setUpRecyclerView(booksViewModel.getReadBooks())
        }

    }

    private fun setUpRecyclerView(filteredList: List<Books>) {
        view?.booksList?.adapter = BooksAdapter(filteredList, this)
        (view?.booksList?.adapter as BooksAdapter).notifyDataSetChanged()

    }

    override fun listItemClicked(books: Books) {
        //Navigate using shared element animations on item click
        //Make the fragment transitions
        val cardPair = card to getString(R.string.transition_card)
        val extraInfoForSharedElements = FragmentNavigatorExtras(
            cardPair
        )
        val action =
            BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(
                books.bookId
            )
        findNavController().navigate(action, extraInfoForSharedElements)
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
                    setUpRecyclerView(newList)
                }
            }
            else -> {
                lifecycleScope.launch {
                    val newList = booksViewModel.sortData("Self Help")
                    setUpRecyclerView(newList)
                }
            }
        }
    }

}