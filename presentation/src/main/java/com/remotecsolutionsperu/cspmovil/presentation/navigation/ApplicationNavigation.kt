package com.remotecsolutionsperu.cspmovil.presentation.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.auth.splash.SplashViewModel

@SuppressLint("RestrictedApi")
@Composable
fun ApplicationNavigation(
    modifier: Modifier = Modifier,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    var isAuthenticated by remember { mutableStateOf(splashViewModel.hasUser()) }

    if (isAuthenticated) {
        Log.d(
            "ApplicationNavigation",
            "MainNavigation.isAuthenticated: $isAuthenticated"
        )
        MainNavigation(onSignOut = {
            splashViewModel.signOut()
            isAuthenticated = false
        })
    } else {
        AuthNavigation(onAuthComplete = { isAuthenticated = true })
        Log.d("ApplicationNavigation", "isAuthenticated: $isAuthenticated")
    }
}