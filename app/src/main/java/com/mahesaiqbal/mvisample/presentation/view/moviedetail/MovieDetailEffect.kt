package com.mahesaiqbal.mvisample.presentation.view.moviedetail

sealed class MovieDetailEffect {
    data class ShowInfo(val message: String) : MovieDetailEffect()
    data object NavigateBack : MovieDetailEffect()
}