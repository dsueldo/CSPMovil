package com.remotecsolutionsperu.cspmovil.ui.screens.signIn

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.signIn.SignInViewModel
import com.remotecsolutionsperu.cspmovil.ui.theme.Black
import com.remotecsolutionsperu.cspmovil.ui.theme.CSPMovilTheme
import com.remotecsolutionsperu.cspmovil.ui.theme.Red_Dark
import com.remotecsolutionsperu.presentation.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SignInScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val email by signInViewModel.email.collectAsState()
    val password by signInViewModel.password.collectAsState()
    val isLoading by signInViewModel.isLoading.collectAsState()
    val errorMessage by signInViewModel.errorMessage.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))

            Text(
                text = stringResource(R.string.login_screen_subtitle),
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 5.sp
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))

            Text(
                text = stringResource(R.string.login_screen_subtitle_2),
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 5.sp
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp))

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp)
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
                onValueChange = { signInViewModel.updateEmail(it) },
                placeholder = { Text(stringResource(R.string.login_screen_enter_email)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
            )

            OutlinedTextField(
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp)
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
                onValueChange = { signInViewModel.updatePassword(it) },
                placeholder = { Text(stringResource(R.string.login_screen_password)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Email") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))

            Button(
                onClick = { signInViewModel.onSignInClick(openAndPopUp) },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red_Dark,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.login_screen_success),
                    fontSize = 16.sp,
                    modifier = modifier.padding(0.dp, 6.dp)
                )
            }

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp))

            TextButton(onClick = { signInViewModel.onSignUpClick(openAndPopUp) }) {
                Text(
                    text = stringResource(R.string.sign_up_description),
                    fontSize = 16.sp,
                    color = Black
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthPreview() {
    CSPMovilTheme() {
        SignInScreen({ _, _ -> })
    }
}