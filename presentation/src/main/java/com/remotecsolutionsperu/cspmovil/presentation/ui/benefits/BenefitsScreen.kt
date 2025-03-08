package com.remotecsolutionsperu.cspmovil.presentation.ui.benefits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.BenefitComponent

@Composable
fun BenefitsScreen(
    benefitList: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        items(benefitList) { benefit ->
            BenefitComponent(
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ),
                image = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                title = "Beca de Ingeniero Global, 60% de descuento",
                content = "Beneficio exclusivo para los ingenieros colegiados habilitados y familiares",
                onNavigateWhatsapp = {}
            )
        }
    }
}

@Preview
@Composable
private fun BenefitsScreenPreview() {
    BenefitsScreen(
        benefitList = listOf("benefit1", "benefit2")
    )
}
