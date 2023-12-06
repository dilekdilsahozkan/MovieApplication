package com.example.movieapplication.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentMovieBinding
import com.example.movieapplication.presentation.adapter.MovieListAdapter
import com.example.movieapplication.presentation.adapter.SliderPageAdapter
import com.example.movieapplication.presentation.viewmodel.MovieViewModel
import com.example.movieapplication.presentation.viewmodel.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    private val movieListAdapter by lazy {
        MovieListAdapter(
            onRowClick = {
                findNavController().navigate(
                    R.id.action_movieFragment_to_movieDetailFragment,
                    bundleOf(
                        MovieActivity.BUNDLE_ID to it.id
                    )
                )
            }
        )
    }

    private val movieSliderAdapter: SliderPageAdapter by lazy {
        SliderPageAdapter(
            context,
            onRowClick = {
                findNavController().navigate(
                    R.id.action_movieFragment_to_movieDetailFragment,
                    bundleOf(
                        MovieActivity.BUNDLE_ID to it.id,
                    )
                )
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        viewModel.getNowPlayingMovie()
        viewModel.getUpcomingMovie()

        addObserver()

        binding.recyclerView.adapter = movieListAdapter
        binding.viewPager.adapter = movieSliderAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        return binding.root
    }

    private fun addObserver() {
        lifecycleScope.launch {
            viewModel.fetchState.collect {
                when (it) {
                    is ViewState.Success -> {
                        movieListAdapter.submitList(it.data.results)
                    }

                    is ViewState.Error -> {
                        println(it.message)
                    }

                    else -> {}
                }
            }
        }
        lifecycleScope.launch {
            viewModel.playingState.collect {
                when (it) {
                    is ViewState.Success -> {
                        movieSliderAdapter.setData(it.data)
                    }

                    is ViewState.Error -> {
                        println(it.message)
                    }

                    else -> {}
                }
            }
        }
    }
}