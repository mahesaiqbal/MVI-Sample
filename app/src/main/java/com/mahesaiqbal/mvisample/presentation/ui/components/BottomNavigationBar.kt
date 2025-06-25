package com.mahesaiqbal.mvisample.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mahesaiqbal.mvisample.presentation.ui.navigation.BottomBarScreen
import com.mahesaiqbal.mvisample.presentation.ui.navigation.bottomBarItems

@Composable
fun BottomNavigationBar(
    currentBottomBarScreen: BottomBarScreen,
    onNavigationSelected: (BottomBarScreen) -> Unit
) {
    NavigationBar {
        bottomBarItems.forEach { destination ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = "$destination icon"
                    )
                },
                label = {
                    Text(
                        text = destination.title,
                        color = Color.DarkGray
                    )
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.LightGray),
                selected = currentBottomBarScreen == destination,
                onClick = { onNavigationSelected(destination) }
            )
        }
    }
}