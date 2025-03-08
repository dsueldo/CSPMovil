package com.remotecsolutionsperu.cspmovil.presentation.ui.signUp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.CSPMovilTheme
import com.remotecsolutionsperu.presentation.R
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signUp.SignUpViewModel
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Black
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Red_Dark

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val email by signUpViewModel.email.collectAsState()
    val password by signUpViewModel.password.collectAsState()
    val confirmPassword = signUpViewModel.confirmPassword.collectAsState()
    val isLoading by signUpViewModel.isLoading.collectAsState()
    val errorMessage by signUpViewModel.errorMessage.collectAsState()

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

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp))

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = Black),
                        shape = RoundedCornerShape(50)
                    ),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                value = email,
                onValueChange = { signUpViewModel.updateEmail(it) },
                placeholder = { Text(stringResource(R.string.login_screen_enter_email)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
            )

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = Black),
                        shape = RoundedCornerShape(50)
                    ),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                value = password,
                onValueChange = { signUpViewModel.updatePassword(it) },
                placeholder = { Text(stringResource(R.string.login_screen_password)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Email") },
                visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = Black),
                        shape = RoundedCornerShape(50)
                    ),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                value = confirmPassword.value,
                onValueChange = { signUpViewModel.updateConfirmPassword(it) },
                placeholder = { Text(stringResource(R.string.login_screen_confirm_password)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Email") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp))

            Button(
                onClick = { signUpViewModel.onSignUpClick(openAndPopUp) },
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthPreview() {
    CSPMovilTheme() {
        SignUpScreen({ _, _ -> })
    }
}