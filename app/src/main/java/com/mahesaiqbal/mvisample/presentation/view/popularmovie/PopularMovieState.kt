package com.mahesaiqbal.mvisample.presentation.view.popularmovie

import com.mahesaiqbal.mvisample.domain.model.Movie

data class PopularMovieState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)
