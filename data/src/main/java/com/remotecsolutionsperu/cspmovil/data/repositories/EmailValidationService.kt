package com.remotecsolutionsperu.cspmovil.data.repositories

import androidx.compose.ui.text.input.TextFieldValue
import com.remotecsolutionsperu.cspmovil.domain.repositories.ValidationService

class EmailValidationService : ValidationService {

    private var errorMessage: String = ""

    override fun validate(input: TextFieldValue): Boolean {
        return if (input.text.isEmpty()) {
            errorMessage = "Ingrese el correo"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(input.text).matches()) {
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