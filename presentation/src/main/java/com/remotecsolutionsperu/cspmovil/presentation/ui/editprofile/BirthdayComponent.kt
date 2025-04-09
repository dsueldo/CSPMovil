package com.remotecsolutionsperu.cspmovil.presentation.ui.editprofile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayComponent(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
) {

    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(text = "OK", color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancelar", color = Color.Black)
            }
        },
        content = {
            DatePicker(
                title = {
                    Text(
                        text = "Cumpleaños",
                        color = Color.Black,
                        style = Typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                },
                headline = {
                    Text(
                        text = "Selecciona tu fecha de nacimiento",
                        color = Color.Black,
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                },

                state = datePickerState,
                colors = DatePickerDefaults.colors(),
            )
        },
    )
}

@Preview
@Composable
private fun BirthdayComponentPreview() {
    BirthdayComponent(
        onDateSelected = {},
        onDismiss = {}
    )
}