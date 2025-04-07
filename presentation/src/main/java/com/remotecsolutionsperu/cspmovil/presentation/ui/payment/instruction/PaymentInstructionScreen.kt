package com.remotecsolutionsperu.cspmovil.presentation.ui.payment.instruction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PaymentInstructionScreen(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        topBar = {
            PaymentInstructionHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        },
        content = { padding ->
            PaymentInstructionBody(
                modifier = Modifier
                    .padding(padding)
                    .padding(top = 16.dp)
                    .verticalScroll(rememberScrollState())
            )
        },
        bottomBar = {
            PaymentInstructionFooter(
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate("paymentInstruction") }
            )
        }
    )

}

@Preview
@Composable
private fun PaymentInstructionScreenPreview() {
    PaymentInstructionScreen()
}