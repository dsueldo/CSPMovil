package com.remotecsolutionsperu.cspmovil.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.remotecsolutionsperu.presentation.R
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.auth.AuthState
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.auth.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navigateToHome:() -> Unit,
) {
    val loginUiState = authViewModel.loginUiState
    val authState by authViewModel.authState.observeAsState()

    LaunchedEffect(key1 = authState) {
        if (authState is AuthState.Authenticated) {
            navigateToHome()
        }
    }

    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            model = "https://csp-limacallao.org.pe/wp-content/uploads/2021/06/98e94bdf-ce87-464d-8333-f0cefec1e270.jpg",
            contentDescription = null,
            alignment = Alignment.Center,
        )
        Text(
            text = stringResource(id = R.string.login_screen_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                ),
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.login_screen_subtitle),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginUiState.code,
            onValueChange = { authViewModel.updateCode(it) },
            label = {
                Text (text = stringResource(id = R.string.login_screen_code), color = Color.White)
            },
            placeholder = {
                Text(text = stringResource(id = R.string.login_screen_enter_code), color = Color.White)
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginUiState.password,
            onValueChange = { authViewModel.updatePassword(it) },
            label = {
                Text(text = stringResource(id = R.string.login_screen_password), color = Color.White)
            },
            placeholder = {
                Text(text = stringResource(id = R.string.login_screen_enter_password), color = Color.White)
            },
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            text = stringResource(id = R.string.login_screen_forget_password),
            color = Color.Red,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = {}
            )
            Text(
                text = stringResource(id = R.string.login_screen_accept_terms_conditions),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = { authViewModel.signUp(
                email = loginUiState.code,
                password = loginUiState.password,
                onResult = { navigateToHome() }
            ) },
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth(),
            content = {
                Text(
                    text = stringResource(id = R.string.login_screen_success),
                    color = Color.White,
                    fontSize = 16.sp
                )
            },
            enabled = loginUiState.code.isNotEmpty() && loginUiState.password.isNotEmpty()
        )
        if (loginUiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        if (loginUiState.error != null) {
            Text(text = "Error: ${loginUiState.error}", color = Color.Red)
        }
    }
}