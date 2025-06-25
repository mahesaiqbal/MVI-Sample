package com.mahesaiqbal.mvisample.presentation.ui.navigation

import androidx.compose.runtime.saveable.Saver
import androidx.navigation3.runtime.NavKey
import com.mahesaiqbal.mvisample.R
import kotlinx.serialization.Serializable

val bottomBarItems = listOf<BottomBarScreen>(
    BottomBarScreen.Popular,
    BottomBarScreen.NowPlaying,
    BottomBarScreen.Upcoming
)

@Serializable
sealed class BottomBarScreen(
    val icon: Int,
    val title: String,
) : NavKey {
    @Serializable
    data object Popular : BottomBarScreen(
        icon = R.drawable.ic_popular_movie,
        title = "Popular Movies"
    )

    @Serializable
    data object NowPlaying : BottomBarScreen(
        icon = R.drawable.ic_now_playing_movie,
        title = "Now Playing Movies"
    )

    @Serializable
    data object Upcoming : BottomBarScreen(
        icon = R.drawable.ic_upcoming_movie,
        title = "Upcoming Movies"
    )
}

val BottomBarScreenSaver = Saver<BottomBarScreen, String>(
    save = { it::class.simpleName ?: "Unknown" },
    restore = {
        when (it) {
            BottomBarScreen.Popular::class.simpleName -> BottomBarScreen.Popular
            BottomBarScreen.NowPlaying::class.simpleName -> BottomBarScreen.NowPlaying
            BottomBarScreen.Upcoming::class.simpleName -> BottomBarScreen.Upcoming
            else -> BottomBarScreen.Popular
        }
    }
)