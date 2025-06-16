package com.mahesaiqbal.mvisample.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.mahesaiqbal.mvisample.R
import com.mahesaiqbal.mvisample.presentation.ui.navigation.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    modifier: Modifier = Modifier,
    onNavigationSelected: (String) -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.popular_movies),
                icon = Icons.Rounded.Home,
                screen = Screen.Popular
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = Color.DarkGray
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = Color.DarkGray
                    )
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.LightGray),
                selected = currentRoute == item.screen.route,
                onClick = { onNavigationSelected(item.screen.route) }
            )
        }
    }
}