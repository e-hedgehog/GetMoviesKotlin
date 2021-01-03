package com.ehedgehog.android.getmovieskotlin

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ehedgehog.android.getmovieskotlin.network.MoviesSearchItem
import com.ehedgehog.android.getmovieskotlin.screens.moviesSearch.MoviesAdapter

@BindingAdapter("listItems")
fun setListItems(recyclerView: RecyclerView, data: List<MoviesSearchItem>?) {
    val adapter = recyclerView.adapter as MoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun setImageByUrl(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty() && url != "N/A")
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .fitCenter()
            .into(imageView)
}