package com.remotecsolutionsperu.cspmovil.application

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.presentation.navigation.NEWS_LIST_SCREEN
import com.remotecsolutionsperu.cspmovil.presentation.navigation.SIGN_IN_SCREEN
import com.remotecsolutionsperu.cspmovil.presentation.navigation.SIGN_UP_SCREEN
import com.remotecsolutionsperu.cspmovil.presentation.navigation.SPLASH_SCREEN
import com.remotecsolutionsperu.cspmovil.presentation.ui.news.NewsListScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.signIn.SignInScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.signUp.SignUpScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.splash.SplashScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CspApp() {
    /*CSPMovilTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()
            var selectedItem by remember { mutableStateOf(0) }

            val items = listOf(
                BottomNavItem("Noticias", Icons.Default.Star, selectedItem == 0),
                BottomNavItem("Convenios", Icons.Default.Lock, selectedItem == 1),
                BottomNavItem("Pagos", Icons.Default.ShoppingCart, selectedItem == 2),
                BottomNavItem("Perfil", Icons.Default.Person, selectedItem == 3)
            )

            Scaffold(
                bottomBar = {
                    BottomNavigationDrawer(items) { item ->
                        selectedItem = items.indexOf(item)
                    }
                }
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    cspGraph(appState)
                }
            }
        }
    }*/
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        CspAppState(navController)
    }

fun NavGraphBuilder.cspGraph(appState: CspAppState) {

    composable(SPLASH_SCREEN) {
        SplashScreen()
    }

    composable(SIGN_IN_SCREEN) {
        SignInScreen(
            openAndPopUp = { route, popUp ->
                appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(NEWS_LIST_SCREEN) {
        NewsListScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) },
            newList = listOf("new1", "new2", "new3")
        )
    }
}