package com.remotecsolutionsperu.cspmovil.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Grey40
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography

@Composable
fun ItemPaymentComponent(
    image: String,
    title: String,
    content: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            model = image,
            contentDescription = "Avatar",
        )

        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {

            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = Typography.bodyMedium
                    )
                    Text(
                        text = content,
                        style = Typography.bodySmall,
                        color = Grey40
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Arrow",
                    tint = Color.Black
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        }


    }


}

@Preview
@Composable
private fun ItemPaymentComponentPreview() {
    ItemPaymentComponent(
        image = "url",
        title = "Cuotas Sociales Ordinarias",
        content = "Contenido",
        onClick = {}
    )
}
