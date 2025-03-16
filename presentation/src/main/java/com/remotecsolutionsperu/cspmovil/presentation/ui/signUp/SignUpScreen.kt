package com.remotecsolutionsperu.cspmovil.presentation.ui.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.remotecsolutionsperu.cspmovil.presentation.navigation.Login
import com.remotecsolutionsperu.presentation.R
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Red_Dark
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signUp.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val email by signUpViewModel.email.collectAsState()
    val password by signUpViewModel.password.collectAsState()
    val confirmPassword = signUpViewModel.confirmPassword.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val uiState by signUpViewModel.uiState.collectAsState()
    val isLoading by signUpViewModel.isLoading.collectAsState()
    var errorMessage by remember { mutableStateOf("") }
    val errorAccountValidationMessage by signUpViewModel.errorMessage.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var passwordStrength by remember { mutableStateOf(signUpViewModel.validatePasswordStrength(password)) }
    var showPasswordStrength by remember { mutableStateOf(false) }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }

    LaunchedEffect(uiState) {
        if (uiState) {
            showDialog = true
        }
    }

    if (isLoading) {
        BasicAlertDialog(
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
                signUpViewModel.resetState()
            },
            confirmButton = {
                Button(
                    onClick = {
                        showErrorDialog = false
                        signUpViewModel.resetState()
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

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                signUpViewModel.resetState()
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        signUpViewModel.resetState()
                        navController.navigate(Login)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red_Dark,
                        contentColor = Color.White
                    )
                ) {
                    Text("OK")
                }
            },
            title = { Text("Registro Exitoso") },
            text = { Text("Te has registrado correctamente.") }
        )
    }

    if (errorAccountValidationMessage == "El correo ya se encuentra registrado.") {
        AlertDialog(
            onDismissRequest = {
                signUpViewModel.resetState()
            },
            confirmButton = {
                Button(onClick = {
                    signUpViewModel.resetState()
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red_Dark,
                        contentColor = Color.White
                    )
                ) {
                    Text("Ok")
                }
            },
            title = { Text("Cuenta Registrada") },
            text = { Text("El correo ya se encuentra registrado.") }
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .focusRequester(emailFocusRequester),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                value = email,
                onValueChange = {
                    signUpViewModel.updateEmail(it)
                    signUpViewModel.resetState()
                },
                label = {
                    Text(
                        text = stringResource(R.string.login_screen_email),
                        style = Typography.bodyLarge,
                        color = Color.Black,
                    )
                },
                placeholder = { Text(stringResource(R.string.login_screen_enter_email)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = Color.Black
                    )
                }
            )

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .focusRequester(passwordFocusRequester),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                value = password,
                onValueChange = {
                    signUpViewModel.updatePassword(it)
                    signUpViewModel.resetState()
                },
                label = {
                    Text(
                        text = stringResource(R.string.login_screen_password),
                        style = Typography.bodyLarge,
                        color = Color.Black,
                    )
                },
                placeholder = { Text(stringResource(R.string.login_screen_enter_password)) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = (!passwordVisible) }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription =
                            if (passwordVisible)
                                stringResource(R.string.login_screen_hide_password)
                            else
                                stringResource(R.string.login_screen_show_password),
                            tint = Color.Black
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = Color.Black
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
                keyboardActions = KeyboardActions(onNext = { confirmPasswordFocusRequester.requestFocus() })
            )

            if (showPasswordStrength) {
                Text(
                    text = passwordStrength,
                    color = if (passwordStrength == "La contraseña es fuerte") Color.Blue else Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                value = confirmPassword.value,
                onValueChange = {
                    signUpViewModel.updateConfirmPassword(it)
                    signUpViewModel.resetState()
                },
                label = {
                    Text(
                        text = stringResource(R.string.login_screen_confirm_password),
                        style = Typography.bodyLarge,
                        color = Color.Black,
                    )
                },
                placeholder = { Text(stringResource(R.string.login_screen_enter_password)) },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = (!confirmPasswordVisible) }) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription =
                            if (confirmPasswordVisible)
                                stringResource(R.string.login_screen_hide_password)
                            else
                                stringResource(R.string.login_screen_show_password),
                            tint = Color.Black
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "ConfirmPassword",
                        tint = Color.Black
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { /* Handle done action */ })
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            Button(
                onClick = {
                    val isPasswordValid = signUpViewModel.validateEnterPassword()
                    val isConfirmPasswordValid = signUpViewModel.validateEnterConfirmPassword()
                    val isEmailValid = signUpViewModel.validateEnterEmail()
                    if (isEmailValid && isPasswordValid && isConfirmPasswordValid) {
                        if (signUpViewModel.password.value != signUpViewModel.confirmPassword.value) {
                            errorMessage = "Las contraseñas no coinciden."
                            showErrorDialog = true
                        } else {
                            signUpViewModel.onSignUpClick()
                        }
                    } else {
                        showErrorDialog = true
                        errorMessage = signUpViewModel.errorMessage.value
                    }
                    passwordStrength = signUpViewModel.validatePasswordStrength(password)
                    showPasswordStrength = true
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red_Dark,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.login_screen_register),
                    fontSize = 16.sp,
                    modifier = modifier.padding(0.dp, 6.dp)
                )
            }
        }
    }
}