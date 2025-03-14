package com.remotecsolutionsperu.cspmovil.data.repositories

import com.remotecsolutionsperu.cspmovil.domain.repositories.ValidationService

class PasswordValidationService : ValidationService {
    private var errorMessage: String = ""

    override fun validate(input: String): Boolean {
        return when {
            input.isEmpty() -> {
                errorMessage = "Ingrese la contraseña"
                false
            }
            else -> true
        }
    }

    override fun getErrorMessage(): String {
        return errorMessage
    }
}