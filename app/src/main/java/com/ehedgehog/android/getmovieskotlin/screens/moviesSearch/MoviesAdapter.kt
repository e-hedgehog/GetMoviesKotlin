package com.ehedgehog.android.getmovieskotlin.screens.moviesSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ehedgehog.android.getmovieskotlin.R
import com.ehedgehog.android.getmovieskotlin.databinding.ItemListMovieBinding
import com.ehedgehog.android.getmovieskotlin.network.MoviesSearchItem

class MoviesAdapter(private val clickListener: (movie: MoviesSearchItem) -> Unit) :
    ListAdapter<MoviesSearchItem, MoviesAdapter.MovieViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemListMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_list_movie, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener { clickListener(movie) }
        holder.bind(movie)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MoviesSearchItem>() {
        override fun areItemsTheSame(oldItem: MoviesSearchItem, newItem: MoviesSearchItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MoviesSearchItem, newItem: MoviesSearchItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class MovieViewHolder(private val binding: ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesSearchItem) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

}