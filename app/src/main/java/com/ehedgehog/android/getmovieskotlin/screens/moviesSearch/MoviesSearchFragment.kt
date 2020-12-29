package com.ehedgehog.android.getmovieskotlin.screens.moviesSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ehedgehog.android.getmovieskotlin.R
import com.ehedgehog.android.getmovieskotlin.databinding.FragmentMoviesSearchBinding

class MoviesSearchFragment : Fragment() {

    private lateinit var binding: FragmentMoviesSearchBinding
    private lateinit var viewModel: MoviesSearchViewModel

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
            findNavController().navigate(MoviesSearchFragmentDirections.actionMoviesSearchToMovieDetails())
        }

        return binding.root
    }

}