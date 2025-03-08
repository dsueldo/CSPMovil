package com.remotecsolutionsperu.cspmovil.presentation.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography

@Composable
fun LoginBody(
    onNavigateChangePassword: () -> Unit,
    modifier: Modifier = Modifier,
    enrollmentNumber: TextFieldValue,
    enrollmentNumberValueChange: (TextFieldValue) -> Unit,
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
            value = enrollmentNumber,
            onValueChange = enrollmentNumberValueChange,
            label = {
                Text(
                    text = "Nro. Colegiatura",
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese Nro. Colegiatura",
                    style = Typography.bodySmall
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onNext = {})
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = passwordValueChange,
            label = {
                Text(
                    text = "Contraseña",
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese Contraseña",
                    style = Typography.bodySmall
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibleValueChange(!passwordVisible) }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = {})
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .clickable { onNavigateChangePassword() },
            textAlign = TextAlign.End,
            text = "Olvidaste tu contraseña",
            color = MaterialTheme.colorScheme.onBackground,
            textDecoration = TextDecoration.Underline,
            style = Typography.bodySmall
        )
    }
}

//@Preview
//@Composable
//private fun LoginBodyPreview() {
//    LoginBody(
//        onNavigateChangePassword = {}
//    )
//}
