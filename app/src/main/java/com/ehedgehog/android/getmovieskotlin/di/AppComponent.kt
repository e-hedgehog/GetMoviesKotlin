package com.ehedgehog.android.getmovieskotlin.di

import com.ehedgehog.android.getmovieskotlin.screens.details.MovieDetailsViewModel
import com.ehedgehog.android.getmovieskotlin.screens.moviesSearch.MoviesSearchViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    fun injectMoviesSearchViewModel(viewModel: MoviesSearchViewModel)
    fun injectMovieDetailsViewModel(viewModel: MovieDetailsViewModel)

}