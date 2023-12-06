package com.example.movieapplication.domain

import com.example.movieapplication.data.remote.dto.MovieDetailDto
import com.example.movieapplication.data.remote.dto.MovieDto
import com.example.movieapplication.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    fun getNowPlayingMovie(): Flow<BaseResult<MovieDto>> {
        return flow {
            val value = movieRepository.getNowPlayingMovie()

            if (value.isSuccessful && value.code() == 200) {
                emit(
                    BaseResult.Success(
                        value.body() ?: MovieDto()
                    )
                )
            }
        }
    }

    fun getUpcomingMovie(): Flow<BaseResult<MovieDto>> {
        return flow {
            val value = movieRepository.getUpcomingMovie()

            if (value.isSuccessful && value.code() == 200) {
                emit(
                    BaseResult.Success(
                        value.body() ?: MovieDto()
                    )
                )
            }
        }
    }

    fun getMovieDetail(movieId: String): Flow<BaseResult<MovieDetailDto>> {
        return flow {
            val value = movieRepository.getMovieDetail(movieId)

            if (value.isSuccessful && value.code() == 200) {
                emit(
                    BaseResult.Success(
                        value.body() ?: MovieDetailDto()
                    )
                )
            }
        }
    }
}