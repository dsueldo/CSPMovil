package com.remotecsolutionsperu.cspmovil

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.presentation.ui.home.HomeScreen
import com.remotecsolutionsperu.presentation.ui.theme.CSPMovilTheme
import com.remotecsolutionsperu.presentation.ui.login.LoginScreen
import com.remotecsolutionsperu.presentation.ui.main.MainScreen
import com.remotecsolutionsperu.presentation.ui.splash.SplashScreen
import com.remotecsolutionsperu.presentation.viewmodels.auth.AuthViewModel
import com.remotecsolutionsperu.presentation.viewmodels.auth.AuthViewModelFactory
import com.remotecsolutionsperu.presentation.viewmodels.home.HomeViewModel
import com.remotecsolutionsperu.presentation.viewmodels.home.LoginViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authViewModelFactory: AuthViewModelFactory

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private lateinit var authViewModel: AuthViewModel
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        authViewModel = ViewModelProvider(this, authViewModelFactory)[AuthViewModel::class.java]
        homeViewModel = ViewModelProvider(this, loginViewModelFactory)[HomeViewModel::class.java]
        setContent {
            CSPMovilTheme {
                AppNavigation(authViewModel, homeViewModel)
                //MainScreen(feedList = listOf("Feed", "Mi Cuenta", "Beneficios", "Pagos"))
                /*BenefitsScreen(benefitList = listOf(
                    "benefit1", "benefit2", "benefit3", "benefit4", "benefit5", "benefit6", "benefit7", "benefit8", "benefit9", "benefit10"
                ))*/
//                PaymentOneScreen(paymentList = listOf("payment1", "payment2", "payment3","payment4","payment5","payment6","payment7","payment8","payment9","payment10"))
            }
        }
    }
}

@Composable
fun AppNavigation(authViewModel: AuthViewModel, homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    val feedList = remember { listOf("Feed", "Mi Cuenta", "Beneficios", "Pagos") }

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navigateToLogin = {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                navigateToHome = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("main") {
            MainScreen(
                feedList = feedList,
                authViewModel = authViewModel,
                homeViewModel = homeViewModel
            )
        }
    }
}