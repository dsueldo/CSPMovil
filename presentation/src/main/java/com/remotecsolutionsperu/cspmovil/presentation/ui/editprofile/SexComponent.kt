package com.remotecsolutionsperu.cspmovil.presentation.ui.editprofile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography

data class Sexo(val id: String, val name: String)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SexComponent(modifier: Modifier = Modifier) {

    val sexList = listOf(
        Sexo(id = "1", name = "Hombre"),
        Sexo(id = "2", name = "Mujer")
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedSex by remember { mutableStateOf(sexList[0]) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black,
            ),
            value = TextFieldValue(selectedSex.name),
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    text = "Género",
                    style = Typography.bodySmall,
                    color = Color.Black
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            sexList.forEach { sex ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = sex.name,
                            style = Typography.bodySmall
                        )
                    },
                    onClick = {
                        selectedSex = sex
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SexComponentPreview() {
    SexComponent()
}