package com.remotecsolutionsperu.cspmovil.application

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.ui.screens.news.NewsListScreen
import com.remotecsolutionsperu.cspmovil.ui.screens.signIn.SignInScreen
import com.remotecsolutionsperu.cspmovil.ui.screens.signUp.SignUpScreen
import com.remotecsolutionsperu.cspmovil.ui.screens.splash.SplashScreen
import com.remotecsolutionsperu.cspmovil.ui.theme.CSPMovilTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CspApp() {
    CSPMovilTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            Scaffold { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    cspGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        CspAppState(navController)
    }

fun NavGraphBuilder.cspGraph(appState: CspAppState) {

    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_IN_SCREEN) {
        SignInScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
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

    /*composable(
        route = "$NOTE_SCREEN$NEW_ID_ARG",
        arguments = listOf(navArgument(NEW_ID) { defaultValue = NEW_DEFAULT_ID })
    ) {
        NoteScreen(
            noteId = it.arguments?.getString(NEW_ID) ?: NEW_DEFAULT_ID,
            popUpScreen = { appState.popUp() },
            restartApp = { route -> appState.clearAndNavigate(route) }
        )
    }*/
}