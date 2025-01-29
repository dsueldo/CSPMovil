package com.remotecsolutionsperu.presentation.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.remotecsolutionsperu.presentation.repository.login.LoginRepository
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(loginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}