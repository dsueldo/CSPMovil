package com.remotecsolutionsperu.cspmovil.presentation.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography

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
            style = Typography.headlineLarge
        )

        Button(onClick = onEditAccount) {
            Text(text = "Editar cuenta")
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