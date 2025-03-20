package com.remotecsolutionsperu.cspmovil.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.presentation.navigation.EditProfile
import com.remotecsolutionsperu.cspmovil.presentation.navigation.Login
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.profile.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val profileData by viewModel.profileData.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    if (isLoading) {
        BasicAlertDialog(
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

    if (errorMessage.isNotEmpty()) {
        Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
    }

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp),
        topBar = {
            profileData?.let {
                ProfileHeader(
                    name = it.name,
                    onEditAccount = { navController.navigate(EditProfile) }
                )
            }
        },
        bottomBar = {
            ProfileFooter(
                onSignOut = {
                    navController.navigate(Login)

                }
            )
        }
    ) { paddingValues ->
        ProfileBody(
            profileItem = profileData,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()
                ),
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}