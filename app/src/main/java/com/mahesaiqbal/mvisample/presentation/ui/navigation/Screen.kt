package com.mahesaiqbal.mvisample.presentation.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen: NavKey {
    @Serializable
    data object Movies : Screen()

    @Serializable
    data class MovieDetail(val movieId: Int) : Screen()
}