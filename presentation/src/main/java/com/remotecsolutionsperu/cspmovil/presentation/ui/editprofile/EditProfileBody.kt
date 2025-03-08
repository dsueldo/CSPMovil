package com.remotecsolutionsperu.cspmovil.presentation.ui.editprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography

@Composable
fun EditProfileBody(modifier: Modifier = Modifier) {

    var showModalInput by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(TextFieldValue()) }
    var lastName by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue()) }
    var dni by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = {
                Text(
                    text = "Nombre",
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su nombre",
                    style = Typography.bodySmall
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onNext = {})
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            onValueChange = { lastName = it },
            label = {
                Text(
                    text = "Apellido",
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su apellido",
                    style = Typography.bodySmall
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onNext = {})
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Correo",
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su correo",
                    style = Typography.bodySmall
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onNext = {})
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = {
                Text(
                    text = "Telefono",
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su telefono",
                    style = Typography.bodySmall
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            keyboardActions = KeyboardActions(onNext = {})
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dni,
            onValueChange = { dni = it },
            label = {
                Text(
                    text = "D.N.I.",
                    style = Typography.bodySmall
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su D.N.I.",
                    style = Typography.bodySmall
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(onNext = {})
        )

        SexComponent()

        if (showModalInput) {
            BirthdayComponent(
                onDateSelected = {
                    showModalInput = false
                },
                onDismiss = { showModalInput = false }
            )
        }
    }
}

@Preview
@Composable
private fun EditProfileBodyPreview() {
    EditProfileBody()
}