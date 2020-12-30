package com.ehedgehog.android.getmovieskotlin.screens.moviesSearch

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
            val adapter = ArrayAdapter.createFromResource(it, R.array.types_array, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.moviesSearchSpinner.adapter = adapter

            binding.moviesSearchSpinner.setSelection(MoviesPreferences.getStoredTypeIndex(it))
        }

        binding.moviesSearchSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                context?.let { MoviesPreferences.setStoredTypeIndex(it, position) }
                val query = context?.let { MoviesPreferences.getStoredQuery(it) }
                viewModel.searchMovies(query, binding.moviesSearchSpinner.selectedItem as String, 1)
            }

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
                viewModel.searchMovies(query, binding.moviesSearchSpinner.selectedItem as String, 1)

                return false
            }
        })
    }

}