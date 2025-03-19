package com.remotecsolutionsperu.cspmovil.presentation.ui.benefits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.BenefitComponent
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.benefits.BenefitsListViewModel

@Composable
fun BenefitsScreen(
    modifier: Modifier = Modifier,
    viewModel: BenefitsListViewModel = hiltViewModel(),
) {

    val benefitList by viewModel.benefitsList.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = "Convenios",
            style = Typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            items(benefitList) { benefitsItem ->
                BenefitComponent(
                    modifier = Modifier.padding(
                        vertical = 8.dp,
                        horizontal = 0.dp
                    ),
                    image = benefitsItem.image,
                    title = benefitsItem.title,
                    content = benefitsItem.content,
                    onNavigateWhatsapp = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun BenefitsScreenPreview() {
    BenefitsScreen()
}
