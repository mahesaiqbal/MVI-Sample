package com.mahesaiqbal.mvisample.presentation.view.popularmovie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mahesaiqbal.mvisample.presentation.common.ui.FailureScreen
import com.mahesaiqbal.mvisample.presentation.common.ui.LoadingScreen
import com.mahesaiqbal.mvisample.presentation.common.ui.MovieItem
import com.mahesaiqbal.mvisample.utils.DateFormatHelper

@Composable
fun PopularMovieScreenContent(
    modifier: Modifier = Modifier,
    state: PopularMovieState = PopularMovieState(),
    onNavigateToDetail: (Int) -> Unit = {},
    onTryAgain: () -> Unit = {}
) {
    val stateGrid = rememberLazyGridState()
    val mutableInteractionSource = remember { MutableInteractionSource() }

    when {
        state.isLoading -> LoadingScreen()
        state.movies.isNotEmpty() -> {
            LazyVerticalGrid(
                state = stateGrid,
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val popularMovies = state.movies
                items(popularMovies, key = { it.id }) { movie ->
                    MovieItem(
                        title = movie.title,
                        posterPath = movie.posterPath,
                        releaseDate = DateFormatHelper.getFormattedDate(movie.releaseDate),
                        modifier = modifier.clickable(
                            indication = null,
                            interactionSource = mutableInteractionSource
                        ) { onNavigateToDetail(movie.id) }
                    )
                }
            }
        }
        state.error?.isNotEmpty() == true -> FailureScreen(
            message = state.error,
            onTryAgain = { onTryAgain() }
        )
    }
}