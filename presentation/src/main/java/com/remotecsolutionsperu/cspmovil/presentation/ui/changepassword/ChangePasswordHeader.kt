package com.remotecsolutionsperu.cspmovil.presentation.ui.changepassword

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.HeaderComponente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordHeader(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    HeaderComponente(
        title = "Cambiar Contraseña",
        onBack = onBack,
        modifier = modifier
    )
}

@Preview
@Composable
private fun ChangePasswordHeaderPreview() {
    ChangePasswordHeader(
        onBack = {}
    )
}