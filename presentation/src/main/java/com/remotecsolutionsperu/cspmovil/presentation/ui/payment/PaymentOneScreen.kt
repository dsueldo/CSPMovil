package com.remotecsolutionsperu.cspmovil.presentation.ui.payment

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.presentation.navigation.PaymentInstructions
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.ItemPaymentComponent

@Composable
fun PaymentOneScreen(
    navController: NavController,
    paymentList: List<String>,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        items(paymentList) { payment ->
            ItemPaymentComponent(
                image = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                title = "Cuotas Sociales Ordinarias",
                content = "Contenido",
                onClick = { navController.navigate(PaymentInstructions) }
            )
        }
    }

}

@Preview
@Composable
private fun PaymentOneScreenPreview() {
    PaymentOneScreen(
        navController = rememberNavController(),
        paymentList = listOf("payment1", "payment2", "payment3")
    )
}
