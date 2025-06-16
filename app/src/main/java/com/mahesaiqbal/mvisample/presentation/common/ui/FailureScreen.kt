package com.mahesaiqbal.mvisample.presentation.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FailureScreen(
    message: String,
    onTryAgain: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            color = Color.Red
        )

        if (onTryAgain != null) {
            Button(
                onClick = onTryAgain
            ) {
                Text("Please Try Again")
            }
        }
    }
}