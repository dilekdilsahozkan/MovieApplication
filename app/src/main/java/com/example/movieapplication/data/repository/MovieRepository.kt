package com.example.movieapplication.data.repository

import com.example.movieapplication.data.remote.api.MovieService
import com.example.movieapplication.data.remote.dto.MovieDetailDto
import com.example.movieapplication.data.remote.dto.MovieDto
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: MovieService) {
    suspend fun getNowPlayingMovie() : Response<MovieDto> = service.getNowPlayingMovie()
    suspend fun getUpcomingMovie() : Response<MovieDto> = service.getUpcomingMovie()
    suspend fun getMovieDetail(media_id: String) : Response<MovieDetailDto> = service.getMovieDetail(media_id)
}