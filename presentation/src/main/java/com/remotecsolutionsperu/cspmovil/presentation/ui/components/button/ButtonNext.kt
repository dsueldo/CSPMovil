package com.remotecsolutionsperu.cspmovil.presentation.ui.components.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NextButton(
    name: String = "Next",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        shape = RoundedCornerShape(32.dp),
        onClick = { onClick() },
        icon = {
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "Extended floating action button.",
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
       },
        text = { Text(text = name) },
    )
}

@Preview
@Composable
private fun NextButtonPreview() {
    NextButton(
        name = "Next",
        onClick = {}
    )
}