package com.remotecsolutionsperu.cspmovil.ui.screens.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationDrawer(
    items: List<BottomNavItem>,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = item.isSelected,
                onClick = { onItemClick(item) }
            )
        }
    }
}

data class BottomNavItem(
    val name: String,
    val icon: ImageVector,
    val isSelected: Boolean = false
)