package com.example.movieapplication.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.movieapplication.data.remote.dto.MovieDto
import com.example.movieapplication.data.remote.dto.MovieListDto
import com.example.movieapplication.databinding.ItemMovieSliderBinding

class SliderPageAdapter(
    private val context: Context?,
    private var data:  MovieDto? = null,
    private val onRowClick: ((result: MovieListDto) -> Unit)? = null
) : PagerAdapter() {

    fun setData(newData: MovieDto) {
        data = newData
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context ?: container.context)
        val binding: ItemMovieSliderBinding =
            ItemMovieSliderBinding.inflate(inflater, container, false)

        val item: MovieListDto = data?.results?.get(position) ?: MovieListDto()
        binding.item = item

        binding.root.setOnClickListener {
            onRowClick?.invoke(item)
        }

        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int {
        return data?.results?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

