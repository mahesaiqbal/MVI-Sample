package com.mahesaiqbal.mvisample.presentation.view.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mahesaiqbal.mvisample.presentation.ui.navigation.Screen
import com.mahesaiqbal.mvisample.presentation.ui.navigation.Screen.MovieDetail
import com.mahesaiqbal.mvisample.presentation.ui.navigation.Screen.Movies
import com.mahesaiqbal.mvisample.presentation.view.moviedetail.MovieDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp() {
    val backStack = rememberNavBackStack<Screen>(Movies)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Movies> {
                MoviesScreen(
                    onNavigateToDetail = { movieId -> backStack.add(MovieDetail(movieId)) }
                )
            }
            entry<MovieDetail> {
                MovieDetailScreen(
                    movieId = it.movieId,
                    onNavigateBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}