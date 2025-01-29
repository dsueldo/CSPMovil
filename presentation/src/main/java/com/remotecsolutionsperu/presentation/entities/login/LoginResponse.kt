package com.remotecsolutionsperu.presentation.entities.login

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: String,
)