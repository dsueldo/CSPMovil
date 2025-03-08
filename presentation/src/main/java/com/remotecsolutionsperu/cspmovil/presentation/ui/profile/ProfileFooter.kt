package com.remotecsolutionsperu.cspmovil.presentation.ui.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileFooter(
    onSignOut: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier,
        onClick = onSignOut,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Cerrar sesion"
        )
    }
}

@Preview
@Composable
private fun ProfileFooterPreview() {
    ProfileFooter()
}