package com.remotecsolutionsperu.cspmovil.domain.repositories

interface ValidationService {
    fun validate(input: String): Boolean
    fun getErrorMessage(): String
}