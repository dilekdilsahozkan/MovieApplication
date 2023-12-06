package com.example.movieapplication.data.remote.api

import com.example.movieapplication.data.remote.dto.MovieDetailDto
import com.example.movieapplication.data.remote.dto.MovieDto
import com.example.movieapplication.presentation.constants.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieDto>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieDetailDto>
}