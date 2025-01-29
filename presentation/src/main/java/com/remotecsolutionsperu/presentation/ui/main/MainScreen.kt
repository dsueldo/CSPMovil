package com.remotecsolutionsperu.presentation.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.presentation.ui.components.FeedComponent
import com.remotecsolutionsperu.presentation.ui.home.HomeScreen
import com.remotecsolutionsperu.presentation.ui.login.LoginScreen
import com.remotecsolutionsperu.presentation.viewmodels.auth.AuthState
import com.remotecsolutionsperu.presentation.viewmodels.auth.AuthViewModel
import com.remotecsolutionsperu.presentation.viewmodels.home.HomeViewModel

@Composable
fun MainScreen(
    feedList: List<String>,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,
) {

    val navController = rememberNavController()
    val authState by authViewModel.authState.observeAsState()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(feedList) { feed ->
            FeedComponent(
                image = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                title = "Beca de Ingeniero Global, 60% de descuento",
                content = "Beneficio exclusivo para los ingenieros colegiados habilitados y familiares"
            )
        }
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                navigateToHome = { navController.navigate("main") }
            )
        }
        composable("main") {
            val context = LocalContext.current
            val token = remember { authViewModel.idToken }
            HomeScreen(homeViewModel = homeViewModel, token = token ?: "")
        }
    }
    if (authState is AuthState.Authenticated) {
        navController.navigate("main") {
            popUpTo("login") { inclusive = true }
        }
    }
}
