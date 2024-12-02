package com.remotecsolutionsperu.presentation.ui.benefits

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.presentation.ui.Typography
import com.remotecsolutionsperu.presentation.ui.components.BenefitComponent

@Composable
fun BenefitsScreen(
    benefitList: List<String>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Beneficios",
            style = Typography.headlineMedium
        )

        LazyColumn(modifier = modifier) {
            items(benefitList) { benefit ->
                BenefitComponent(
                    modifier = Modifier.padding(bottom = 16.dp),
                    icon = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                    discountText = "discountText",
                    expirationDateText = "expirationDateText",
                    conditionsText = "conditionsText"
                )
            }
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
