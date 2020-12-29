package com.ehedgehog.android.getmovieskotlin.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ehedgehog.android.getmovieskotlin.R
import com.ehedgehog.android.getmovieskotlin.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val currentMovieId = MovieDetailsFragmentArgs.fromBundle(arguments!!).movieId
        viewModel.initMovie(currentMovieId)

        viewModel.movieId.observe(this, Observer { movieId ->
            viewModel.getMovieById(movieId)
        })

        return binding.root
    }

}