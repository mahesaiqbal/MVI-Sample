package com.mahesaiqbal.mvisample.presentation.view.moviedetail

import com.mahesaiqbal.mvisample.domain.model.Movie

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: Movie? = null,
    val error: String? = null
)