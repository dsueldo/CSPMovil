package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.remotecsolutionsperu.cspmovil.application.NEW_DEFAULT_ID
import com.remotecsolutionsperu.cspmovil.application.SPLASH_SCREEN
import com.remotecsolutionsperu.cspmovil.entities.news.News
import com.remotecsolutionsperu.cspmovil.entities.user.UserProfile
import com.remotecsolutionsperu.cspmovil.net.AccountService
import com.remotecsolutionsperu.cspmovil.net.NewsService
import com.remotecsolutionsperu.cspmovil.repository.login.UserRepository
import com.remotecsolutionsperu.cspmovil.repository.auth.Result
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.CspAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repository: UserRepository,
    private val accountService: AccountService,
    private val newsService: NewsService,
) : CspAppViewModel() {

    private val _newsList = MutableStateFlow<List<String>>(emptyList())
    val newsList: StateFlow<List<String>> = _newsList

    init {
        fetchNewsList()
    }

    private fun fetchNewsList() {
        viewModelScope.launch {
            val news = newsService.getNewsList()
            _newsList.value = news
        }
    }

    var userProfileUiState by mutableStateOf(UserProfileUiState())
        private set

    fun initialize(noteId: String, restartApp: (String) -> Unit) {
        observeAuthenticationState(restartApp)
    }

    private fun observeAuthenticationState(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(SPLASH_SCREEN)
            }
        }
    }

    fun getUserProfile(token: String) {
        userProfileUiState = userProfileUiState.copy(isLoading = true, error = null)
        viewModelScope.launch {
            when (val result = repository.getUserProfile(token)) {
                is Result.Success -> {
                    userProfileUiState = userProfileUiState.copy(
                        isLoading = false,
                        isSuccess = true,
                        userProfile = result.data
                    )
                }
                is Result.Failure -> {
                    userProfileUiState = userProfileUiState.copy(
                        error = result.exception.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onSignOutClick() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun onDeleteAccountClick() {
        launchCatching {
            accountService.deleteAccount()
        }
    }

    companion object {
        private val DEFAULT_NOTE = News(NEW_DEFAULT_ID, "My News")
    }
}

data class UserProfileUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val userProfile: UserProfile? = null
)