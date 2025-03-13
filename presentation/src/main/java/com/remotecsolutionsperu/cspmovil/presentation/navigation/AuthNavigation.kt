package com.remotecsolutionsperu.cspmovil.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.presentation.ui.changepassword.ChangePasswordScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.login.LoginScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.signUp.SignUpScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.splash.SplashScreen

@Composable
fun AuthNavigation(onAuthComplete: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                navController = navController
            )
        }
        composable<Login> {
            LoginScreen(
                navController = navController,
                onAuthComplete = onAuthComplete
            )
        }
        composable<SignUp> {
            SignUpScreen(navController = navController)
        }
        composable<ChangePassword> {
            ChangePasswordScreen(navController = navController)
        }
    }
}
