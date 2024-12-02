package com.remotecsolutionsperu.presentation.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.remotecsolutionsperu.presentation.ui.components.FeedComponent

@Composable
fun MainScreen(
    feedList: List<String>,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(feedList) { feed ->
            FeedComponent(
                image = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                title = "Beca de Ingeniero Global, 60% de descuento",
                content = "Beneficio exclusivo para los ingenieros colegiados habilitados y familiares"
            )
        }
    }

}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        feedList = listOf("feed1", "feed2", "feed3")
    )
}
