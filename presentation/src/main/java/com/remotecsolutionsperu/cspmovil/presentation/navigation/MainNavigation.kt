package com.remotecsolutionsperu.cspmovil.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.presentation.ui.benefits.BenefitsScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.editprofile.EditProfileScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.main.MainScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.payment.PaymentOneScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.payment.instruction.PaymentInstructionScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.profile.ProfileScreen
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Red_Dark

@SuppressLint("RestrictedApi")
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {
            BottomNavigation(
                backgroundColor = Red_Dark,
                contentColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                topLevelRoutes.forEach { topLevelRoute ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                topLevelRoute.icon,
                                contentDescription = topLevelRoute.name
                            )
                        },
                        label = { Text(topLevelRoute.name) },
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute(topLevelRoute.route.toString(), arguments = null)
                        } == true,
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Main,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Main> { MainScreen() }
            composable<Profile> { ProfileScreen(navController) }
            composable<EditProfile> { EditProfileScreen(navController) }
            composable<Benefit> { BenefitsScreen() }
            composable<Payment> {
                PaymentOneScreen(
                    navController = navController,
                    paymentList = listOf("pay1", "pay2", "pay3", "pay4"))
            }
            composable<PaymentInstructions> {
                PaymentInstructionScreen(
                    navController = navController
                )
            }
        }
    }
}
