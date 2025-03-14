package com.remotecsolutionsperu.cspmovil.data.repositories

import com.remotecsolutionsperu.cspmovil.domain.repositories.ValidationService

class EmailValidationService : ValidationService {

    private var errorMessage: String = ""

    override fun validate(input: String): Boolean {
        return if (input.isEmpty()) {
            errorMessage = "Ingrese el correo"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            errorMessage = "Formato de correo inválido"
            false
        } else {
            true
        }
    }

    override fun getErrorMessage(): String {
        return errorMessage
    }
}