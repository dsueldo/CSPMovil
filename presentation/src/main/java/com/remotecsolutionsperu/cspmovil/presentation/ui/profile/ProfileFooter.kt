package com.remotecsolutionsperu.cspmovil.presentation.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography

@Composable
fun ProfileFooter(
    modifier: Modifier = Modifier,
    onSignOut: () -> Unit,
    onDeleteAccount: () -> Unit
) {
    Column(modifier = modifier) {
        TextButton(
            onClick = onSignOut,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Cerrar sesión",
                color = Color.Black,
                style = Typography.bodyMedium
            )
        }
        TextButton(
            onClick = onDeleteAccount,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Eliminar Cuenta",
                color = Color.Black,
                style = Typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun ProfileFooterPreview() {
    ProfileFooter(
        onSignOut = {},
        onDeleteAccount = {}
    )
}