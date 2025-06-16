package com.mahesaiqbal.mvisample.presentation.view.popularmovie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahesaiqbal.mvisample.presentation.viewmodel.PopularMovieViewModel

@Composable
fun PopularMovieScreen(
    modifier: Modifier = Modifier,
    viewModel: PopularMovieViewModel = hiltViewModel(),
    onShowInfo: (String) -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.onIntent(PopularMovieIntent.GetPopularMovies)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is PopularMovieEffect.ShowInfo -> onShowInfo(effect.message)
                is PopularMovieEffect.NavigateToDetail -> onNavigateToDetail(effect.movieId)
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        PopularMovieScreenContent(
            state = viewModel.state.collectAsState().value,
            onNavigateToDetail = { movieId ->
                viewModel.onIntent(PopularMovieIntent.NavigateToDetail(movieId))
            },
            onTryAgain = {
                viewModel.onIntent(PopularMovieIntent.GetPopularMovies)
            }
        )
    }
}