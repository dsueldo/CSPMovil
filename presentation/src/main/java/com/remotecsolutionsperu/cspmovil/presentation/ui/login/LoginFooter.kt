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
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Red_Dark
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography

@Composable
fun LoginFooter(
    onClickRegistration: () -> Unit,
    onClickLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextButton(
            onClick = onClickRegistration,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(Red_Dark),
        ) {
            Text(
                text = "Registrarse",
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.titleMedium
            )
        }
        TextButton(
            onClick = onClickLogin,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(Red_Dark),
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
    LoginFooter(onClickRegistration = {}, onClickLogin = {})
}
