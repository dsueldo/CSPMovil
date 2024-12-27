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
import coil.compose.AsyncImage

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .background(Color.Black)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        AsyncImage(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            model = "https://csp-limacallao.org.pe/wp-content/uploads/2021/06/98e94bdf-ce87-464d-8333-f0cefec1e270.jpg",
            contentDescription = null,
            alignment = Alignment.Center,
        )
        Text(
            text = "Bienvenido a CSP Móvil",
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
            text = "El app del Colegio de Sociólogos del Perú, Región Lima - Callao",
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
                text = "Acepto los términos y condiciones",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
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