package com.remotecsolutionsperu.cspmovil.domain.repositories

import androidx.compose.ui.text.input.TextFieldValue

interface ValidationService {
    fun validate(input: TextFieldValue): Boolean
    fun getErrorMessage(): String
}