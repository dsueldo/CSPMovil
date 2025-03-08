package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.splash

import com.remotecsolutionsperu.cspmovil.presentation.navigation.SIGN_IN_SCREEN
import com.remotecsolutionsperu.cspmovil.presentation.navigation.SPLASH_SCREEN
import com.remotecsolutionsperu.cspmovil.domain.repositories.AccountService
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val accountService: AccountService
) : CspAppViewModel() {

  fun onAppStart(openAndPopUp: (String, String) -> Unit) {
    if (accountService.hasUser()) openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
    else openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
  }
}
