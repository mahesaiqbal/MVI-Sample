package com.mahesaiqbal.mvisample.presentation.ui.navigation

sealed class Screen(val route: String) {
    data object Popular : Screen("popular")
    data object NowPlaying : Screen("nowPlaying")
    data object TopRated : Screen("topRated")
    data object Upcoming : Screen("upcoming")
    data object MovieDetails : Screen("movieDetails/{movieId}") {
        fun createRoute(movieId: Int) = "movieDetails/$movieId"
    }
}