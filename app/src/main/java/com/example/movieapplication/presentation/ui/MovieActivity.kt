package com.example.movieapplication.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    companion object{
        const val BUNDLE_ID = "id"
    }

    @Inject
    lateinit var repository: MovieRepository

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}