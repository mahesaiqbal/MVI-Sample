package com.mahesaiqbal.mvisample.presentation.view.popularmovie

sealed class PopularMovieEffect {
    data class ShowInfo(val message: String) : PopularMovieEffect()
    data class NavigateToDetail(val movieId: Int) : PopularMovieEffect()
}