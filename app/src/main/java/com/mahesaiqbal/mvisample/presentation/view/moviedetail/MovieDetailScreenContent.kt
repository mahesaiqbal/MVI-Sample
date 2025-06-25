package com.mahesaiqbal.mvisample.presentation.view.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mahesaiqbal.mvisample.R
import com.mahesaiqbal.mvisample.presentation.common.ui.FailureScreen
import com.mahesaiqbal.mvisample.presentation.common.ui.LoadingScreen
import com.mahesaiqbal.mvisample.utils.DateFormatHelper

@Composable
fun MovieDetailScreenContent(
    modifier: Modifier = Modifier,
    state: MovieDetailState = MovieDetailState(),
    onBackClick: () -> Unit = {},
    onTryAgain: () -> Unit = {}
) {
    when {
        state.isLoading -> LoadingScreen()
        state.movieDetail != null -> {
            val movie = state.movieDetail
            val posterPainter = rememberAsyncImagePainter(
                "https://image.tmdb.org/t/p/original${movie.posterPath}"
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box {
                    Image(
                        painter = posterPainter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(500.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    )
                    OutlinedIconButton(
                        onClick = { onBackClick() },
                        shape = CircleShape,
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                            .size(50.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 72.dp)
                ) {
                    Text(
                        text = movie.title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_person),
                                contentDescription = null,
                                tint = Color.Blue
                            )
                            Text(
                                text = movie.popularity.toString(),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_star),
                                contentDescription = null,
                                tint = Color.Gray
                            )
                            Text(
                                text = movie.voteAverage.toString(),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_thumb_up),
                                contentDescription = null,
                                tint = Color.Green
                            )
                            Text(
                                text = movie.voteCount.toString(),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_date_range),
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                        Text(
                            text = DateFormatHelper.getFormattedDate(movie.releaseDate),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Text(
                        text = movie.overview,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(8.dp)
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