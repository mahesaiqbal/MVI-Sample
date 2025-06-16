package com.mahesaiqbal.mvisample.presentation.view.popularmovie

sealed class PopularMovieIntent {
    data object GetPopularMovies : PopularMovieIntent()
    data class NavigateToDetail(val movieId: Int) : PopularMovieIntent()
}