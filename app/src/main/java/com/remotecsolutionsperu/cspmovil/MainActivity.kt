package com.remotecsolutionsperu.cspmovil

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.remotecsolutionsperu.cspmovil.ui.theme.CSPMovilTheme
import com.remotecsolutionsperu.presentation.ui.benefits.BenefitsScreen
import com.remotecsolutionsperu.presentation.ui.main.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSPMovilTheme {
                //MainScreen(feedList = listOf("Feed", "Mi Cuenta", "Beneficios", "Pagos"))
                BenefitsScreen(benefitList = listOf(
                    "benefit1", "benefit2", "benefit3", "benefit4", "benefit5", "benefit6", "benefit7", "benefit8", "benefit9", "benefit10"
                ))
            }
        }
    }
}