package com.remotecsolutionsperu.cspmovil.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.presentation.navigation.SignUp
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signIn.SignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    onAuthComplete: () -> Unit,
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = hiltViewModel(),
) {

    val email by signInViewModel.email.collectAsState()
    val password by signInViewModel.password.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    val uiState by signInViewModel.uiState.collectAsState()
    val isLoading by signInViewModel.isLoading.collectAsState()
    val errorMessage by signInViewModel.errorMessage.collectAsState()

    if (uiState) {
        onAuthComplete()
    }

    if (isLoading) {
        BasicAlertDialog (
            onDismissRequest = { },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            },
        )
    }

    Scaffold(
        modifier = modifier
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        topBar = {
            LoginHeader(
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        content = { padding ->
            LoginBody(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
                onNavigateChangePassword = {
                },
                email = TextFieldValue(email),
                emaiValueChange = { signInViewModel.updateEmail(it.text) },
                password = TextFieldValue(password),
                passwordValueChange = { signInViewModel.updatePassword(it.text) },
                passwordVisible = passwordVisible,
                passwordVisibleValueChange = { passwordVisible = it }
            )
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        },
        bottomBar = {
            LoginFooter(
                onClickRegistration = { navController.navigate(SignUp) },
                onClickLogin = { signInViewModel.onSignInClick() },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    )
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        onAuthComplete = {})
}