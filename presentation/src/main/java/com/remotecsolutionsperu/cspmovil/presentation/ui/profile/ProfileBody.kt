package com.remotecsolutionsperu.cspmovil.presentation.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.ItemProfileComponent

@Composable
fun ProfileBody(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Divider()

        ItemProfileComponent(
            imageVector = Icons.Outlined.PersonOutline,
            title = "Nombre",
            content = "Christian"
        )

        ItemProfileComponent(
            imageVector = Icons.Outlined.PersonOutline,
            title = "Apellido",
            content = "Quispe"
        )

        ItemProfileComponent(
            imageVector = Icons.Outlined.School,
            title = "Numero de Colegiatura",
            content = "000000"
        )

        ItemProfileComponent(
            imageVector = Icons.Outlined.People,
            title = "Genero",
            content = "Masculino"
        )

        ItemProfileComponent(
            imageVector = Icons.Outlined.Fingerprint,
            title = "D.N.I.",
            content = "111111"
        )

        ItemProfileComponent(
            imageVector = Icons.Outlined.CalendarToday,
            title = "Cumpleanos",
            content = "12/12/24"
        )

        ItemProfileComponent(
            imageVector = Icons.Outlined.Email,
            title = "Correo",
            content = "hola@gmail.com"
        )

        ItemProfileComponent(
            imageVector = Icons.Outlined.Phone,
            title = "Celular",
            content = "1212111"
        )

        Divider()

    }

}

@Preview
@Composable
private fun ProfileBodyPreview() {
    ProfileBody()
}