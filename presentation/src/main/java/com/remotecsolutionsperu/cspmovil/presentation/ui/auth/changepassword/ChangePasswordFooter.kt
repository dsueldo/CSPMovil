package com.remotecsolutionsperu.cspmovil.presentation.ui.auth.changepassword

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Red_Dark
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography

@Composable
fun ChangePasswordFooter(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    TextButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(Red_Dark),
    ) {
        Text(
            text = "Enviar",
            color = MaterialTheme.colorScheme.onPrimary,
            style = Typography.titleSmall
        )
    }

}

@Preview
@Composable
private fun ChangePasswordFooterPreview() {
    ChangePasswordFooter(
        onClick = {}
    )
}