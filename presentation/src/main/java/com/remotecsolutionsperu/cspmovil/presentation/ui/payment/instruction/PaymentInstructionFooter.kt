package com.remotecsolutionsperu.cspmovil.presentation.ui.payment.instruction

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.button.FooterButtonComponent

@Composable
fun PaymentInstructionFooter(
    onBack: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    FooterButtonComponent(
        modifier = modifier,
        onBack = onBack,
        onNext = onNext,
    )
}

@Preview
@Composable
private fun PaymentInstructionFooterPreview() {
    PaymentInstructionFooter(
        onBack = {},
        onNext = {}
    )
}