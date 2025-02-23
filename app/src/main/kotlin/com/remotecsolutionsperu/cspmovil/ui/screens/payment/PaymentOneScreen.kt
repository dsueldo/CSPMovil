package com.remotecsolutionsperu.cspmovil.ui.screens.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.ui.screens.components.ItemPaymentComponent
import com.remotecsolutionsperu.cspmovil.ui.screens.components.TitleComponent

@Composable
fun PaymentOneScreen(
    paymentList: List<String>,
    modifier: Modifier = Modifier
) {

    Column {
        TitleComponent(text = "Pagos")
        LazyColumn(modifier = modifier) {
            items(paymentList) { payment ->
                ItemPaymentComponent(
                    image = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                    title = "Cuotas Sociales Ordinarias",
                    content = "Contenido",
                    onClick = {}
                )
            }
        }
    }

}

@Preview
@Composable
private fun PaymentOneScreenPreview() {
    PaymentOneScreen(
        listOf("payment1", "payment2", "payment3")
    )
}
