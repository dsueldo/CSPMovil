package com.remotecsolutionsperu.presentation.ui.login

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .background(Color.Black)
            .padding(32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            model = "https://drive.google.com/drive/folders/1bITRk0GqWhLypebVZ2yI93OuYv6wUIYV",
            contentDescription = null,
        )
        Text(
            text = "Bienvenido a CSP Móvil",
            modifier = Modifier.fillMaxWidth(),
            color = Color.White
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "El app del Consejo Departamental de Lima - Callao",
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = TextFieldValue(),
            onValueChange = {},
            label = {
                Text(text = "Nro. Colegiatura", color = Color.White)
            },
            placeholder = {
                Text(text = "Ingrese Nro. Colegiatura", color = Color.White)
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = TextFieldValue(),
            onValueChange = {},
            label = {
                Text(text = "Contraseña", color = Color.White)
            },
            placeholder = {
                Text(text = "Ingrese Contraseña", color = Color.White)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            text = "Olvidaste tu contraseña",
            color = Color.Red,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = {}
            )
            Text(
                text = "Acepto los términos y condiciones",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = {},
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth(),
            content = {
                Text(
                    text = "Ingresar",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}