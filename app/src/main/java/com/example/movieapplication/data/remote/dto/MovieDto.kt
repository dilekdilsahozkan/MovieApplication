package com.example.movieapplication.data.remote.dto

data class MovieDto(
    var results: List<MovieListDto>? = null
)

data class MovieListDto(
    val id: Int ?= null,
    var poster_path:String ?= null,
    var title: String ?= null,
    var overview: String ?= null,
    var release_date: String ?= null
)