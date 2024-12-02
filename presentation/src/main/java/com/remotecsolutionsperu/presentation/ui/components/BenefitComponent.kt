package com.remotecsolutionsperu.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.remotecsolutionsperu.presentation.ui.Grey60
import com.remotecsolutionsperu.presentation.ui.Grey90
import com.remotecsolutionsperu.presentation.ui.Typography

@Composable
fun BenefitComponent(
    icon: String,
    discountText: String,
    expirationDateText: String,
    conditionsText: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = Grey60),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(0.75f)) {
                Text(
                    text = discountText,
                    style = Typography.headlineMedium
                )
                Spacer(Modifier.height(24.dp))
                Text(
                    text = expirationDateText,
                    style = Typography.bodySmall
                )
                Text(
                    text = conditionsText,
                    style = Typography.bodySmall
                )

                Spacer(Modifier.height(24.dp))
                Row {
                    FilledTonalButton(
                        onClick = {},
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = Grey90
                        )
                    ) {
                        Text(
                            text = "Shop",
                            color = Color.Black
                        )
                    }

                    TextButton(onClick = {}) {
                        Text(
                            text = "Detalle",
                            color = Color.Black
                        )
                    }
                }

            }

            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.End
            ) {
                AsyncImage(
                    modifier = Modifier.size(60.dp),
                    model = icon,
                    contentDescription = null
                )
            }

        }
    }

}

@Preview
@Composable
private fun BenefitComponentPreview() {
    BenefitComponent(
        icon = "https://www.pngwing.com/es/free-png-ytitz",
        discountText = "discountText",
        expirationDateText = "expirationDateText",
        conditionsText = "conditionsText"
    )
}
