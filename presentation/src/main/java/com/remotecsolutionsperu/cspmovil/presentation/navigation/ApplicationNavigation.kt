package com.remotecsolutionsperu.cspmovil.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.splash.SplashViewModel

@SuppressLint("RestrictedApi")
@Composable
fun ApplicationNavigation(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {

    var isAuthenticated by remember { mutableStateOf(viewModel.hasUser()) }

    if (isAuthenticated) {
        MainNavigation()
    } else {
        AuthNavigation(onAuthComplete = { isAuthenticated = true })
    }

}