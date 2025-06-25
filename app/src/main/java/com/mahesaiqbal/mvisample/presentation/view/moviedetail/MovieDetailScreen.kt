package com.mahesaiqbal.mvisample.presentation.view.moviedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahesaiqbal.mvisample.presentation.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onIntent(MovieDetailIntent.GetMovieDetail(movieId))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MovieDetailEffect.NavigateBack -> onNavigateBack()
                is MovieDetailEffect.ShowInfo -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MovieDetailScreenContent(
                state = viewModel.state.collectAsState().value,
                onBackClick = { viewModel.onIntent(MovieDetailIntent.NavigateBack) },
                onTryAgain = { viewModel.onIntent(MovieDetailIntent.GetMovieDetail(movieId)) }
            )
        }
    }
}