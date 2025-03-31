package com.remotecsolutionsperu.cspmovil.presentation.ui.editprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.editprofile.EditProfileViewModel

@Composable
fun EditProfileBody(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel,
) {
    val profileUiState by viewModel.profileUiState.collectAsState()
    var showModalInput by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
    ) {
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            value = profileUiState.name,
            onValueChange = { viewModel.updateName(it) },
            label = {
                Text(
                    text = "Nombres",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese sus nombres",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onNext = {}),
            textStyle = Typography.bodySmall
        )

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            value = profileUiState.lastName,
            onValueChange = { viewModel.updateLastName(it) },
            label = {
                Text(
                    text = "Apellidos",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese sus apellidos",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onNext = {}),
            textStyle = Typography.bodySmall
        )

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            value = profileUiState.phone,
            onValueChange = { viewModel.updatePhoneNumber(it) },
            label = {
                Text(
                    text = "Celular",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su celular",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            keyboardActions = KeyboardActions(onNext = {}),
            textStyle = Typography.bodySmall
        )

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            value = profileUiState.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = {
                Text(
                    text = "Correo",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su correo",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onNext = {}),
            textStyle = Typography.bodySmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        SexComponent()

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            value = profileUiState.birthday,
            onValueChange = { viewModel.updateBirthday(it) },
            label = {
                Text(
                    text = "Cumpleaños",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su cumpleaños",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(onNext = {}),
            textStyle = Typography.bodySmall
        )

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black
            ),
            value = profileUiState.dni,
            onValueChange = { viewModel.updateDni(it) },
            label = {
                Text(
                    text = "D.N.I.",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            placeholder = {
                Text(
                    text = "Ingrese su D.N.I.",
                    style = Typography.bodySmall,
                    color = Color.Black,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(onNext = {}),
            textStyle = Typography.bodySmall
        )

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