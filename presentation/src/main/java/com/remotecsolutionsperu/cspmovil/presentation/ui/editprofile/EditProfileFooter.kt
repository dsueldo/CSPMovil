package com.remotecsolutionsperu.cspmovil.presentation.ui.editprofile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Red_Dark
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography

@Composable
fun EditProfileFooter(
    modifier: Modifier = Modifier,
    onClickSave: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClickSave,
        colors = ButtonDefaults.textButtonColors(Red_Dark),
    ) {
        Text(
            text = "Guardar",
            color = MaterialTheme.colorScheme.onPrimary,
            style = Typography.titleSmall
        )
    }
}

@Preview
@Composable
private fun EditProfileFooterPreview() {
    EditProfileFooter(onClickSave = {})
}