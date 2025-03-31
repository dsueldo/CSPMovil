package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.auth.splash

import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val accountService: AccountService,
) : CspAppViewModel() {

  fun hasUser(): Boolean {
    return accountService.hasUser()
  }

  fun signOut() {
    viewModelScope.launch {
      accountService.signOut()
    }
  }
}
