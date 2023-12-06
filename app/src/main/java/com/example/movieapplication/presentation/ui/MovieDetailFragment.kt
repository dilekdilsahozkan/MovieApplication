package com.example.movieapplication.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentMovieBinding
import com.example.movieapplication.databinding.FragmentMovieDetailBinding
import com.example.movieapplication.presentation.adapter.loadImage
import com.example.movieapplication.presentation.constants.Constants
import com.example.movieapplication.presentation.viewmodel.MovieViewModel
import com.example.movieapplication.presentation.viewmodel.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var viewModel: MovieViewModel

    private val movieId: Int? by lazy {
        arguments?.getInt(MovieActivity.BUNDLE_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        movieId?.let { viewModel.getMovieDetail(it.toString()) }
        addObserver()

        return binding.root
    }

    private fun addObserver() {
        lifecycleScope.launch {
            viewModel.detailState.collect {
                when (it) {
                    is ViewState.Success -> {
                        binding.item = it.data
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