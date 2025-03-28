package com.remotecsolutionsperu.cspmovil.presentation.ui.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.remotecsolutionsperu.cspmovil.presentation.navigation.ChangePassword
import com.remotecsolutionsperu.cspmovil.presentation.navigation.SignUp
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Red_Dark
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.auth.signIn.SignInViewModel

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

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val uiState by signInViewModel.uiState.collectAsState()
    val isLoading by signInViewModel.isLoading.collectAsState()

    var errorMessage by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    val errorPasswordValidationMessage by signInViewModel.errorMessage.collectAsState()


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

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = {
                showErrorDialog = false
                signInViewModel.resetState()
            },
            confirmButton = {
                Button(
                    onClick = {
                        showErrorDialog = false
                        signInViewModel.resetState()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red_Dark,
                        contentColor = Color.White
                    )
                ) {
                    Text("OK")
                }
            },
            title = { Text("Alerta!") },
            text = { Text(errorMessage) }
        )
    }

    if (errorPasswordValidationMessage == "Credenciales incorrectas. Intente de nuevo.") {
        AlertDialog(
            onDismissRequest = {
                signInViewModel.resetState()
            },
            confirmButton = {
                Button(onClick = {
                    signInViewModel.resetState()
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red_Dark,
                        contentColor = Color.White
                    )
                ) {
                    Text("Ok")
                }
            },
            title = { Text("Contraseña Incorrecta") },
            text = { Text("La contraseña ingresada es incorrecta. Intente de nuevo.") }
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
                onNavigateChangePassword = { navController.navigate(ChangePassword)},
                email = email,
                emailValueChange = { signInViewModel.updateEmail(it) },
                password = password,
                passwordValueChange = {
                    signInViewModel.updatePassword(it)
                },
                passwordVisible = passwordVisible,
                passwordVisibleValueChange = { passwordVisible = it },
                emailFocusRequester = emailFocusRequester,
                passwordFocusRequester = passwordFocusRequester,
            )
        },
        bottomBar = {
            LoginFooter(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClickLogin = {
                    val isPasswordValid = signInViewModel.validateEnterPassword()
                    val isEmailValid = signInViewModel.validateEnterEmail()
                    if (isEmailValid && isPasswordValid) {
                        signInViewModel.onSignInClick()
                    } else {
                        showErrorDialog = true
                        errorMessage = signInViewModel.errorMessage.value
                    }
                },
                onClickRegistration = {
                    signInViewModel.clearUserData()
                    navController.navigate(SignUp)
                },
            )
        }
    )
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        onAuthComplete = {}
    )
}