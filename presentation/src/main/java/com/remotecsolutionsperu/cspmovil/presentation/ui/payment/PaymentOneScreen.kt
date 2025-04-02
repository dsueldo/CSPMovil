package com.remotecsolutionsperu.cspmovil.presentation.ui.payment

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.presentation.navigation.PaymentInstructions
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.ItemPaymentComponent
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Red_Dark
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.payment.PaymentsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentOneScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PaymentsViewModel = hiltViewModel(),
) {

    val paymentsList by viewModel.paymentsList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        BasicAlertDialog (
            onDismissRequest = { },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Red_Dark
                    )
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
            text = "Pagos",
            style = Typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            items(paymentsList) { paymentsItem ->
                ItemPaymentComponent(
                    image = paymentsItem.image,
                    title = paymentsItem.title,
                    content = paymentsItem.content,
                    onClick = { navController.navigate(PaymentInstructions) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PaymentOneScreenPreview() {
    PaymentOneScreen(navController = rememberNavController())
}
