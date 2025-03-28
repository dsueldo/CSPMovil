package com.remotecsolutionsperu.cspmovil.presentation.ui.benefits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.BenefitComponent
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.benefits.BenefitsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BenefitsScreen(
    modifier: Modifier = Modifier,
    viewModel: BenefitsListViewModel = hiltViewModel(),
) {

    val benefitList by viewModel.benefitsList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    if (isLoading) {
        BasicAlertDialog (
            onDismissRequest = { },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            },
        )
    }

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
