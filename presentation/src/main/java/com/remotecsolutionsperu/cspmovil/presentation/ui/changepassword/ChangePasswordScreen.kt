package com.remotecsolutionsperu.cspmovil.presentation.ui.changepassword

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
fun ChangePasswordScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        topBar = {
            ChangePasswordHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                onBack = { navController.popBackStack() }
            )
        },
        content = { padding ->
            ChangePasswordBody(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            )
        },
        bottomBar = {
            ChangePasswordFooter(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    )
}

@Preview
@Composable
private fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(
        navController = rememberNavController()
    )
}