package com.remotecsolutionsperu.cspmovil.presentation.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Red_Dark
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography

@Composable
fun ProfileHeader(
    name: String,
    onEditAccount: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            textAlign = TextAlign.Center,
            style = Typography.headlineSmall
        )

        Button(
            onClick = onEditAccount,
            colors = ButtonDefaults.textButtonColors(Red_Dark),
        ) {
            Text(
                text = "Editar cuenta",
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun ProfileHeaderPreview() {
    ProfileHeader(
        name = "cristian",
        onEditAccount = {}
    )
}