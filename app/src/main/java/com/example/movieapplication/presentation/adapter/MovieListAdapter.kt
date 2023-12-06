package com.example.movieapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.BR
import com.example.movieapplication.data.remote.dto.MovieListDto
import com.example.movieapplication.databinding.ItemMovieBinding

class MovieListAdapter(
    private val onRowClick: ((result: MovieListDto) -> Unit)? = null
) :
    ListAdapter<MovieListDto, MovieListAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<MovieListDto>() {
        override fun areItemsTheSame(
            oldItem: MovieListDto,
            newItem: MovieListDto
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: MovieListDto,
            newItem: MovieListDto
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.setVariable(BR.item, item)
    }

    inner class ViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rootView.setOnClickListener {
                selectItem(bindingAdapterPosition)
            }
        }
    }

    fun selectItem(position: Int) {
        onRowClick?.invoke(getItem(position))
    }
}