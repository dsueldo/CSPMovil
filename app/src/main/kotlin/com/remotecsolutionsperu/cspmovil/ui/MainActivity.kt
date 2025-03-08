package com.remotecsolutionsperu.cspmovil.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.BuildConfig
import com.google.firebase.firestore.firestore
import com.remotecsolutionsperu.cspmovil.presentation.navigation.AUTH_PORT
import com.remotecsolutionsperu.cspmovil.application.CspApp
import com.remotecsolutionsperu.cspmovil.presentation.navigation.FIRESTORE_PORT
import com.remotecsolutionsperu.cspmovil.presentation.navigation.LOCALHOST
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { CspApp() }
    }

    private fun configureFirebaseServices() {
        if (BuildConfig.DEBUG) {
            Firebase.auth.useEmulator(LOCALHOST, AUTH_PORT)
            Firebase.firestore.useEmulator(LOCALHOST, FIRESTORE_PORT)
        }
    }
}