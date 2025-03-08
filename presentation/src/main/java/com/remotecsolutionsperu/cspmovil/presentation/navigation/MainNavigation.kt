package com.remotecsolutionsperu.cspmovil.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

@SuppressLint("RestrictedApi")
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
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
            composable<Main> { MainScreen(listOf("main1", "main2", "main3", "main4")) }
            composable<Profile> { ProfileScreen(navController) }
            composable<EditProfile> { EditProfileScreen(navController) }
            composable<Benefit> {
                BenefitsScreen(
                    listOf(
                        "benefit1",
                        "benefit2",
                        "benefit3",
                        "benefit4"
                    )
                )
            }
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
