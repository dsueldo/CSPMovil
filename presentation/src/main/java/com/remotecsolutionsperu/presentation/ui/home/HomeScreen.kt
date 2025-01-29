package com.remotecsolutionsperu.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.presentation.viewmodels.home.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    token: String
) {
    val uiState = homeViewModel.userProfileUiState

    LaunchedEffect(Unit) {
        homeViewModel.getUserProfile(token)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else if (uiState.error != null) {
            Text(text = "Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
        } else if (uiState.isSuccess) {
            Text(text = "Welcome, ${uiState.userProfile?.fullName}")
            // Display other user profile information
        }
    }
}