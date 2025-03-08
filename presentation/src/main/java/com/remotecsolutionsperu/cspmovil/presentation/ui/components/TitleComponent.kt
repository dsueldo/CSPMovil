package com.remotecsolutionsperu.cspmovil.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography

@Composable
fun TitleComponent(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        color = Color.Black,
        style = Typography.headlineMedium
    )
}

@Preview
@Composable
private fun TitleComponentPreview() {
    TitleComponent(text = "Title")
}
