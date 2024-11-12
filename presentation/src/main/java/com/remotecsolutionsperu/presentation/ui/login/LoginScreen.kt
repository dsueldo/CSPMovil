package com.remotecsolutionsperu.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.ui.theme.Gray
import com.remotecsolutionsperu.cspmovil.ui.theme.Red

@Preview()
@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Gray, Red)))
    ) {

    }
}