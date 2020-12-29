package com.ehedgehog.android.getmovieskotlin

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ehedgehog.android.getmovieskotlin.network.Movie
import com.ehedgehog.android.getmovieskotlin.network.MoviesSearchItem
import com.ehedgehog.android.getmovieskotlin.screens.moviesSearch.MoviesAdapter

@BindingAdapter("listItems")
fun setListItems(recyclerView: RecyclerView, data: List<MoviesSearchItem>?) {
    val adapter = recyclerView.adapter as MoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun setImageByUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .fitCenter()
        .into(imageView)
}