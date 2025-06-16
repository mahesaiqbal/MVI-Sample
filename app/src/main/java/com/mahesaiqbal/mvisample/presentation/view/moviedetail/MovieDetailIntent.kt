package com.mahesaiqbal.mvisample.presentation.view.moviedetail

sealed class MovieDetailIntent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailIntent()
    data object NavigateBack : MovieDetailIntent()
}