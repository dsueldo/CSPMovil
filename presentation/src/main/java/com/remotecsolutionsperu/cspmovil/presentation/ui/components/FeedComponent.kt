package com.remotecsolutionsperu.cspmovil.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography

@Composable
fun FeedComponent(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    content: String,
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = image,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                fontWeight = FontWeight.Bold,
                style = Typography.bodyLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = content,
                style = Typography.bodyMedium,
                color = Color.Black
            )
        }
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
