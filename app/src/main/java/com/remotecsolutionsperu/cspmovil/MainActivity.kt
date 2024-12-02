package com.remotecsolutionsperu.cspmovil

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.remotecsolutionsperu.cspmovil.ui.theme.CSPMovilTheme
import com.remotecsolutionsperu.presentation.ui.login.LoginScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSPMovilTheme {
                LoginScreen()
            }
        }
    }
}