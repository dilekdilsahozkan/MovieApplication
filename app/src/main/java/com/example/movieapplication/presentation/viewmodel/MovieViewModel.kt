package com.example.movieapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.remote.dto.MovieDetailDto
import com.example.movieapplication.data.remote.dto.MovieDto
import com.example.movieapplication.data.remote.dto.MovieListDto
import com.example.movieapplication.domain.BaseResult
import com.example.movieapplication.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    BaseViewModel<MovieListDto>() {

    var _fetchState: MutableStateFlow<ViewState<MovieDto>> =
        MutableStateFlow(ViewState.Idle())
    val fetchState: StateFlow<ViewState<MovieDto>> = _fetchState

    var _playingState: MutableStateFlow<ViewState<MovieDto>> =
        MutableStateFlow(ViewState.Idle())
    val playingState: StateFlow<ViewState<MovieDto>> = _playingState

    var _detailState: MutableStateFlow<ViewState<MovieDetailDto>> =
        MutableStateFlow(ViewState.Idle())
    val detailState: StateFlow<ViewState<MovieDetailDto>> = _detailState

    fun getNowPlayingMovie() {
        viewModelScope.launch {
            movieUseCase.getNowPlayingMovie()
                .onStart {
                    _playingState.value = ViewState.Idle()
                }
                .catch { exception ->
                    _playingState.value = ViewState.Error(message = exception.message)
                    Log.e("CATCH", "exception : $exception")
                }
                .collect { result ->
                    when (result) {
                        is BaseResult.Success -> {
                            _playingState.value = ViewState.Success(result.data)
                        }

                        is BaseResult.Error -> {
                            _playingState.value = ViewState.Error()
                        }
                    }
                }
        }
    }

    fun getUpcomingMovie() {
        viewModelScope.launch {
            movieUseCase.getUpcomingMovie()
                .onStart {
                    _fetchState.value = ViewState.Idle()
                }
                .catch { exception ->
                    _fetchState.value = ViewState.Error(message = exception.message)
                    Log.e("CATCH", "exception : $exception")
                }
                .collect { result ->
                    when (result) {
                        is BaseResult.Success -> {
                            _fetchState.value = ViewState.Success(result.data)
                        }

                        is BaseResult.Error -> {
                            _fetchState.value = ViewState.Error()
                        }
                    }
                }
        }
    }

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            movieUseCase.getMovieDetail(movieId)
                .onStart {
                    _detailState.value = ViewState.Idle()
                }
                .catch { exception ->
                    _detailState.value = ViewState.Error(message = exception.message)
                    Log.e("CATCH", "exception : $exception")
                }
                .collect { result ->
                    when (result) {
                        is BaseResult.Success -> {
                            _detailState.value = ViewState.Success(result.data)
                        }

                        is BaseResult.Error -> {
                            _detailState.value = ViewState.Error()
                        }
                    }
                }
        }
    }
}