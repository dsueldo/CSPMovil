package com.remotecsolutionsperu.cspmovil.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.remotecsolutionsperu.cspmovil.presentation.navigation.EditProfile

@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp),
        topBar = {
            ProfileHeader(
                name = "Diego",
                onEditAccount = { navController.navigate(EditProfile) }
            )
        },
        bottomBar = {
            ProfileFooter()
        }
    ) { paddingValues ->
        ProfileBody(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        )
    }

}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}