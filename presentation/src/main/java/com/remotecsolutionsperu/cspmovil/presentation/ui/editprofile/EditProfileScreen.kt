package com.remotecsolutionsperu.cspmovil.presentation.ui.editprofile

import androidx.compose.foundation.background
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
fun EditProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        topBar = {
            EditProfileHeader(
                onBack = { navController.popBackStack() },
                modifier = Modifier
            )
        },
        bottomBar = {
            EditProfileFooter(
                onClick = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    ) { paddingValues ->
        EditProfileBody(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        )
    }

}

@Preview
@Composable
private fun EditProfileScreenPreview() {
    EditProfileScreen(
        navController = rememberNavController()
    )
}