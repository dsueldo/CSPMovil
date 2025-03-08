package com.remotecsolutionsperu.cspmovil.presentation.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography

@Composable
fun LoginFooter(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(MaterialTheme.colorScheme.primary),
        ) {
            Text(
                text = "Ingresar",
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
private fun LoginFooterPreview() {
    LoginFooter(onClick = {})
}
