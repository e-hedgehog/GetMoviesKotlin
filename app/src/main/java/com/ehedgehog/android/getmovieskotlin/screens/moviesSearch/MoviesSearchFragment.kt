package com.ehedgehog.android.getmovieskotlin.screens.moviesSearch

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ehedgehog.android.getmovieskotlin.MoviesPreferences
import com.ehedgehog.android.getmovieskotlin.R
import com.ehedgehog.android.getmovieskotlin.databinding.FragmentMoviesSearchBinding
import java.util.*

class MoviesSearchFragment : Fragment() {

    private lateinit var binding: FragmentMoviesSearchBinding
    private lateinit var viewModel: MoviesSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_movies_search, container, false)
        viewModel = ViewModelProvider(this).get(MoviesSearchViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.moviesSearchRecyclerView.adapter = MoviesAdapter {
            findNavController().navigate(MoviesSearchFragmentDirections.actionMoviesSearchToMovieDetails(it.id))
        }

        context?.let {
            val storedQuery = MoviesPreferences.getStoredQuery(it)
            if (!storedQuery.isNullOrEmpty())
                viewModel.searchMovies(storedQuery, "movie", 1)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_movies_search, menu)
        val menuItem = menu.findItem(R.id.menu_search_item)
        val searchView = menuItem.actionView as SearchView
        searchView.setIconifiedByDefault(true)

        searchView.setOnSearchClickListener {
            context?.let {
                val query = MoviesPreferences.getStoredQuery(it)
                searchView.setQuery(query, false)
            }
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.onActionViewCollapsed()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null || newText.length < 3)
                    return false

                val query = newText.trim().toLowerCase(Locale.getDefault())
                context?.let { MoviesPreferences.setStoredQuery(it, query) }
                viewModel.searchMovies(query, "movie", 1)

                return false
            }
        })
    }

}