package com.remotecsolutionsperu.presentation.shared

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.remotecsolutionsperu.presentation.ui.splash.SplashScreen

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
) {

    NavHost(navController = navHostController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(/*navigateToLogin = { navHostController.navigate("logIn") },
                navigateToSignUp = { navHostController.navigate("signUp") }*/)
        }
        composable("logIn") {
            LoginScreen(auth){ navHostController.navigate("home") }
        }
        composable("signUp") {
            SignUpScreen(auth)
        }
        composable("home"){
            HomeScreen()
        }
    }
}
