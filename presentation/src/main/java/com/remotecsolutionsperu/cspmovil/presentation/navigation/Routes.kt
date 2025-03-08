package com.remotecsolutionsperu.cspmovil.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Work
import kotlinx.serialization.Serializable

@Serializable data object Login
@Serializable data object ChangePassword
@Serializable data object Main
@Serializable data object Profile
@Serializable data object EditProfile
@Serializable data object Benefit
@Serializable data object Payment
@Serializable data object PaymentInstructions

val topLevelRoutes = listOf(
    TopLevelRoute("Noticias", Main, Icons.Filled.Newspaper),
    TopLevelRoute("Convenios", Benefit, Icons.Filled.Work),
    TopLevelRoute("Pagos", Payment, Icons.Filled.Money),
    TopLevelRoute("Perfil", Profile, Icons.Filled.Face),
)