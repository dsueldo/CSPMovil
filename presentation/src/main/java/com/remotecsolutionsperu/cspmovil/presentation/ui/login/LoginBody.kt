package com.remotecsolutionsperu.cspmovil.presentation.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography
import com.remotecsolutionsperu.presentation.R

@Composable
fun LoginBody(
    onNavigateChangePassword: () -> Unit,
    modifier: Modifier = Modifier,
    email: TextFieldValue,
    emaiValueChange: (TextFieldValue) -> Unit,
    password: TextFieldValue,
    passwordValueChange: (TextFieldValue) -> Unit,
    passwordVisible: Boolean = false,
    passwordVisibleValueChange: (Boolean) -> Unit,
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = emaiValueChange,
            label = {
                Text(
                    text = stringResource(R.string.login_screen_email),
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.login_screen_enter_email),
                    style = Typography.bodySmall
                )
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
            singleLine = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onNext = {})
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = passwordValueChange,
            label = {
                Text(
                    text = stringResource(R.string.login_screen_password),
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.login_screen_enter_password),
                    style = Typography.bodySmall
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibleValueChange(!passwordVisible) }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription =
                        if (passwordVisible)
                            stringResource(R.string.login_screen_hide_password)
                        else
                            stringResource(R.string.login_screen_show_password),
                    )
                }
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = {})
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .clickable { onNavigateChangePassword() },
            textAlign = TextAlign.End,
            text = stringResource(R.string.login_screen_forget_password),
            color = MaterialTheme.colorScheme.onBackground,
            textDecoration = TextDecoration.Underline,
            style = Typography.bodySmall
        )
    }
}
