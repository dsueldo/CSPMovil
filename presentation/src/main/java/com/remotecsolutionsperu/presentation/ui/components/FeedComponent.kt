package com.remotecsolutionsperu.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.remotecsolutionsperu.presentation.ui.Grey40
import com.remotecsolutionsperu.presentation.ui.Typography

@Composable
fun FeedComponent(
    image: String,
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = image,
            contentDescription = null
        )
        Text(
            text = title,
            style = Typography.headlineMedium,
            color = Color.Black
        )
        Text(
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
