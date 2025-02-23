package com.remotecsolutionsperu.cspmovil.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.remotecsolutionsperu.cspmovil.ui.theme.Grey40
import com.remotecsolutionsperu.cspmovil.ui.theme.Typography

@Composable
fun FeedComponent(
    image: String,
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.padding(16.dp).fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = image,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = Typography.headlineMedium,
            color = Color.Black
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = content,
            style = Typography.bodySmall,
            color = Grey40
        )
    }
}

@Preview
@Composable
private fun FeedComponentPreview() {
    FeedComponent(
        image = "url",
        title = "Beca ingeniero global",
        content = "Beneficio exclusivo"
    )
}
