package com.mahesaiqbal.mvisample.presentation.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mahesaiqbal.mvisample.presentation.ui.theme.MVISampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black
            )
        },
        colors = topAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = actions
    )
}

@Preview(showBackground = true)
@Composable
fun CenterTopAppBarPreview() {
    MVISampleTheme {
        CenterTopAppBar(title = "MVI Sample")
    }
}