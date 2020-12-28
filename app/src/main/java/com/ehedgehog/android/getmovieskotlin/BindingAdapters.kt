package com.ehedgehog.android.getmovieskotlin

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ehedgehog.android.getmovieskotlin.entities.Movie
import com.ehedgehog.android.getmovieskotlin.screens.moviesSearch.MoviesAdapter

@BindingAdapter("listItems")
fun setListItems(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MoviesAdapter
    adapter.submitList(data)
}